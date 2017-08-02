package io.dyy.konachan.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import io.dyy.konachan.R;
import io.dyy.konachan.data.ItemInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dyy on 2017/7/31.
 */


public class OpenImage extends BaseFragment {
    private ImageView imageView;
    private ItemInfo data;
    private TapBarMenu tapBarMenu;
    private Button saveImageButton, imageInfoButton;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //载入图片
                    Glide.with(getContext())
                            .load("https:" + data.getJpeg_url())
                            .thumbnail(Glide.with(getContext())
                                    .load(R.drawable.shaonvqidaozhong))
                            .apply(new RequestOptions())
                            .into(imageView);
                    break;
                case 1:
                    //开始下载
                    Toast.makeText(getContext(), "开始下载原图," + "文件大小为" + String.valueOf(msg.obj), Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getContext(), "下载完成", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getContext(), "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Aria.download(this).register();
        data = (ItemInfo) getArguments().getSerializable("item");
        rootView = inflater.inflate(R.layout.fragment_open_image, container, false);
        BaseInitView();
        initView();
        initTapBarMenu();
        return rootView;
    }

    private void initTapBarMenu() {
        saveImageButton.setTypeface(typeface);
        imageInfoButton.setTypeface(typeface);
        saveImageButton.setOnClickListener(v -> {
            SaveImage();
        });
        imageInfoButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "查看图片信息", Toast.LENGTH_SHORT).show();
        });
        tapBarMenu.setOnClickListener(v -> {
            tapBarMenu.toggle();
        });
    }

    private void SaveImage() {
        if (isPermission()) {
            File path = new File(Environment.getExternalStorageDirectory() + "/konachan");
            if (!path.exists()) {
                path.mkdir();
            }
            new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https:" + data.getFile_url())
                        .get()
                        .build();
//                Toast.makeText(getContext(), "正在下载原图," + "文件大小为" + FormatFileSize(data.getFile_size()), Toast.LENGTH_LONG).show();
                Message StartMsg = Message.obtain();
                StartMsg.what = 1;
                StartMsg.obj = FormatFileSize(data.getFile_size());
                handler.sendMessage(StartMsg);
                String FileType = data.getFile_url().substring(data.getFile_url().lastIndexOf("."));
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("OpenImage", "下载失败");
                        Message FailMsg = Message.obtain();
                        FailMsg.what = 3;
                        handler.sendMessage(FailMsg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("OpenImage", "onResponse执行");
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;
                        // 储存下载文件的目录
                        String savePath = Environment.getExternalStorageDirectory() + "/konachan";
                        try {
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            File file = new File(savePath, data.getId() + FileType);
                            fos = new FileOutputStream(file);
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                // 下载中
                            }
                            fos.flush();
                            Log.d("OpenImage", "下载完成");
                            Message CompleteMsg = Message.obtain();
                            CompleteMsg.what = 2;
                            handler.sendMessage(CompleteMsg);
                            getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path.getPath() + "/" + data.getId() + FileType))));//提醒系统更新图库
                        } catch (Exception e) {
                        } finally {
                            try {
                                if (is != null)
                                    is.close();
                            } catch (IOException e) {
                            }
                            try {
                                if (fos != null)
                                    fos.close();
                            } catch (IOException e) {
                            }
                        }

                    }
                });
            }).start();
            /*Aria.download(getActivity())
                    .load("https:" + data.getFile_url())
                    .setDownloadPath(path.getPath() + "/" + data.getId() + FileType)
                    .start();*/
            Log.d("OpenImage", "data.getFile_size():" + data.getFile_size());
        }

    }

    private String FormatFileSize(double size) {
        if (size / 1024 < 1024) {
            return String.valueOf(new BigDecimal(size / 1024).setScale(2, 4).floatValue()) + "KB";
        } else {
            return String.valueOf(new BigDecimal(size / 1024 / 1024).setScale(2, 4).floatValue()) + "MB";
        }
    }

  /*  @Download.onTaskComplete
    public void taskComplete(DownloadTask task) {
        Toast.makeText(getContext(), "下载完成", Toast.LENGTH_SHORT).show();
        getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(task.getDownloadPath()))));//提醒系统更新图库
    }*/

    private boolean isPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            return true;
        }
        return false;
    }

    private void initView() {
        saveImageButton = (Button) rootView.findViewById(R.id.saveImageButton);
        imageInfoButton = (Button) rootView.findViewById(R.id.imageInfoButton);
        imageView = (ImageView) rootView.findViewById(R.id.showImage);
        tapBarMenu = (TapBarMenu) rootView.findViewById(R.id.imageInfoMenu);
        title.setText(String.valueOf(data.getId()));
        Message message = Message.obtain();
        message.what = 0;
        handler.sendMessage(message);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "没有权限无法保存图片", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
