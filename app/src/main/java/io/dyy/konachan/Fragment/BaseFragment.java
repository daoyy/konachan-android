package io.dyy.konachan.Fragment;

import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import io.dyy.konachan.BaseApplication;
import io.dyy.konachan.R;

/**
 * Created by dyy on 2017/8/2.
 */

public class BaseFragment extends Fragment {
    protected View rootView;
    protected View frameLayout;
    protected TextView title;
    protected FloatingActionButton fab;
    protected Typeface typeface;

    protected void BaseInitView() {
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        frameLayout = getActivity().findViewById(R.id.openImageFrameLayout);
        title = (TextView) rootView.findViewById(R.id.fragment_titile);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    }

    @Override
    public void onPause() {
        super.onPause();
        frameLayout.setVisibility(View.GONE);
        fab.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        frameLayout.setVisibility(View.VISIBLE);
        fab.hide();
    }
}
