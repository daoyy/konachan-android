package io.dyy.konachan.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.dyy.konachan.R;

/**
 * Created by dyy on 2017/8/2.
 */

public class SettingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.setting_layout, container, false);
        BaseInitView();
        initView();
        return rootView;
    }

    private void initView() {
        title.setText("设置");
    }
}
