package io.dyy.konachan;

import android.app.Application;
import android.graphics.Typeface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.dyy.konachan.data.ItemInfo;

/**
 * Created by dyy on 2017/8/1.
 */

public class BaseApplication extends Application {
    public List<ItemInfo> items;

    @Override
    public void onCreate() {
        super.onCreate();
        items = new ArrayList<>();
    }
}
