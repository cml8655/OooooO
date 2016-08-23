package com.cml.second.app.baby.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.activity.ScaleableImageActivity;
import com.cml.second.app.baby.widget.ScaleablePopupWindow;

import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/8/22.
 */
public class ScaleableImageFragment extends BaseFragment {


    @OnClick(R.id.imagescale_img)
    public void onImageClick(View view) {
        Intent intent = new Intent(getActivity(), ScaleableImageActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 10, 10).toBundle();
        ActivityCompat.startActivity(getActivity(), intent, bundle);
    }

    @OnClick(R.id.imagescale_img2)
    public void onImageClick2(View view) {
        ScaleablePopupWindow window = new ScaleablePopupWindow(getActivity());
        window.create();
        window.show(getActivity().getWindow().getDecorView(), 0, 0);
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_scale_img;
    }

    @Override
    public int getTitle() {
        return R.string.title_imagescale;
    }
}
