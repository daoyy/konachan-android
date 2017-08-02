package io.dyy.konachan.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import io.dyy.konachan.R;
import io.dyy.konachan.data.ItemInfo;

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
            Glide.with(getContext())
                    .load("https:" + data.getFile_url())
                    .thumbnail(Glide.with(getContext())
                            .load(R.drawable.shaonvqidaozhong))
                    .apply(new RequestOptions())
                    .into(imageView);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
        });
        imageInfoButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "查看图片信息", Toast.LENGTH_SHORT).show();
        });
        tapBarMenu.setOnClickListener(v -> {
            tapBarMenu.toggle();
        });
    }


    private void initView() {
        saveImageButton = (Button) rootView.findViewById(R.id.saveImageButton);
        imageInfoButton = (Button) rootView.findViewById(R.id.imageInfoButton);
        imageView = (ImageView) rootView.findViewById(R.id.showImage);
        tapBarMenu = (TapBarMenu) rootView.findViewById(R.id.imageInfoMenu);
        title.setText(String.valueOf(data.getId()));
        handler.sendMessage(handler.obtainMessage());
    }
}
