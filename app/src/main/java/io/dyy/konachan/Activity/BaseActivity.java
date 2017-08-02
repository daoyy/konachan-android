package io.dyy.konachan.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import io.dyy.konachan.Fragment.BaseFragment;
import io.dyy.konachan.Fragment.OpenImage;
import io.dyy.konachan.Fragment.SettingFragment;
import io.dyy.konachan.R;
import io.dyy.konachan.data.ItemInfo;

/**
 * Created by dyy on 2017/7/29.
 */

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_setting:
                runFragment(new SettingFragment());
                break;
            case R.id.aboutMe:
                Toast.makeText(this, "点击了关于", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void runFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.openImageFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
