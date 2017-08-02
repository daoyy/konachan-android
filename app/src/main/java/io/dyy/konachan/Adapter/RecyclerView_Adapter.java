package io.dyy.konachan.Adapter;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import io.dyy.konachan.Activity.MainActivity;
import io.dyy.konachan.Fragment.BaseFragment;
import io.dyy.konachan.Fragment.OpenImage;
import io.dyy.konachan.R;
import io.dyy.konachan.data.ItemInfo;

/**
 * Created by dyy on 2017/7/29.
 */

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private void runFragment(BaseFragment fragment, ItemInfo item) {
        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        fragment.setArguments(bundle);
        transaction.replace(R.id.openImageFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    WoZhenCongMing WoZhenCongMing = (WoZhenCongMing) msg.obj;
                    ImageView imageView = (ImageView) WoZhenCongMing.getObject();
                    ItemInfo item = WoZhenCongMing.getItem();
                    imageView.setOnClickListener(v -> {
                        runFragment(new OpenImage(), item);
                    });
                    Glide.with(context)
                            .load("https:" + item.getPreview_url())
                            .thumbnail(Glide.with(context).load(R.drawable.loading))
                            .apply(new RequestOptions().placeholder(R.drawable.image))
                            .into(imageView);
                    break;
            }
        }
    };
    private int normalType = 0;     // 第一种ViewType，正常的item
    private int footType = 1;       // 第二种ViewType，底部的提示View
    private List<ItemInfo> data = new ArrayList<>();
    private Context context;
    private TextView tips;
    private ProgressBar loadProgressBar;

    public RecyclerView_Adapter(List<ItemInfo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.main_item, parent, false));
        } else {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.footview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            ItemInfo item = data.get(position);
            ImageView imageView = ((NormalHolder) holder).thumbnail;
            Message message = Message.obtain();
            message.what = 1;
            message.obj = new WoZhenCongMing(imageView, item);
            handler.sendMessage(message);
        } else {
//            ((FootHolder) holder).tips.setText("加载更多");
            tips = ((FootHolder) holder).tips;
            loadProgressBar = ((FootHolder) holder).loadProgressBar;
        }
    }

    public void showLoadView() {
        if (loadProgressBar != null & tips != null) {
            loadProgressBar.setVisibility(View.VISIBLE);
            tips.setText("正在加载");
        }
    }

    public void hideLoadView() {
        if (loadProgressBar != null & tips != null) {
            loadProgressBar.setVisibility(View.GONE);
            tips.setText("");
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == getItemCount() - 1) {
                        return gridLayoutManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    // 自定义方法，获取列表中数据源的最后一个位置，比getItemCount少1，因为不计上footView

//    public int getRealLastPosition() {
//        return data.size();
//    }

    // 正常item的ViewHolder，用以缓存findView操作
    private class NormalHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;

        NormalHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.Thumbnail);
        }
    }

    // // 底部footView的ViewHolder，用以缓存findView操作
    private class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;
        private ProgressBar loadProgressBar;

        FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.tips);
            loadProgressBar = (ProgressBar) itemView.findViewById(R.id.loadProgressBar);
        }
    }

    class WoZhenCongMing {
        Object object;
        ItemInfo item;

        public Object getObject() {
            return object;
        }

        public ItemInfo getItem() {
            return item;
        }

        public WoZhenCongMing(Object object, ItemInfo item) {
            this.object = object;
            this.item = item;
        }
    }
}
