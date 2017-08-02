package io.dyy.konachan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.dyy.konachan.Adapter.RecyclerView_Adapter;
import io.dyy.konachan.R;
import io.dyy.konachan.BaseApplication;
import io.dyy.konachan.data.ItemInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private BaseApplication application;
    public static final int REFRESH = 1;
    public static final int LOAD_MORE = 2;
    public static final int ERROR = 3;
    private static int PAGE_INDEX = 1;
    private int lastVisibleItem = 0;
    private int firstVisibleItem = 0;
    private int isLoading;
    private GridLayoutManager layoutManager;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    Log.d("MainActivity", "Handler执行了");
                    adapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);
                    break;
                case LOAD_MORE:
                    adapter.notifyDataSetChanged();
                    isLoading = 0;
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.this, "加载失败,请检查网络连接是否正常", Toast.LENGTH_SHORT).show();
//                    items.addAll(((List<ItemInfo>) application.hashMap.get("Cache")));
                    items.addAll(application.items);
                    adapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);
                    break;
            }
        }
    };
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private View frameLayout;
    private FloatingActionButton fab;
    private RecyclerView_Adapter adapter;
    private List<ItemInfo> items = new ArrayList<>();

    private void LoadMoreData() {
        new Thread(() -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://konachan.com/post.json?page=" + PAGE_INDEX++)
                    .get()
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    if (swipeRefresh.isRefreshing()) {
//                        application.hashMap.put("Cache", items);
                        application.items.clear();
                        application.items.addAll(items);
                        items.clear();
                    }
                    parseJSONWithGSON(responseData);
                    Message message = Message.obtain();
                    if (PAGE_INDEX <= 2) {
                        message.what = REFRESH;
                    } else {
                        message.what = LOAD_MORE;
                    }
                    MainActivity.this.handler.sendMessage(message);
                } else {
                    Log.d("MainActivity", "貌似是加载失败了2");
                    isLoading = 0;
                    Message message = Message.obtain();
                    message.what = ERROR;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MainActivity", "异常2");
                isLoading = 0;
                Message message = Message.obtain();
                message.what = ERROR;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (BaseApplication) getApplication();
        initToolbar();
        LoadMoreData();
        initView();
        initRecylerView();
        initSwipeRefreshLayout();
        initFab();
    }

    private void initFab() {
        fab.setOnClickListener(v -> {
            recyclerView.scrollToPosition(0);
        });
    }

    private void parseJSONWithGSON(String jsonData) {
        Log.d("MainActivity", "parseJSONWithGSON执行了");
        Gson gson = new Gson();
        List<ItemInfo> itemList = gson.fromJson(jsonData, new TypeToken<List<ItemInfo>>() {
        }.getType());
        if (!itemList.isEmpty()) {
            items.addAll(itemList);
        }
    }

    private void initView() {
        frameLayout = findViewById(R.id.openImageFrameLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_main_list);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initSwipeRefreshLayout() {
        swipeRefresh.setProgressViewOffset(true, 50, 50);
        swipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefresh.setOnRefreshListener(() -> {
            PAGE_INDEX = 1;
            LoadMoreData();
        });
    }

    private void initRecylerView() {
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerView_Adapter(items, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (firstVisibleItem == 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
                // 在newState为滑到底部时
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    if (isLoading != 1) {
                        LoadMoreData();
                    }
                    isLoading = 1;
                    adapter.showLoadView();
                } else {
                    if (isLoading != 1) {
                        adapter.hideLoadView();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (frameLayout.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

}
