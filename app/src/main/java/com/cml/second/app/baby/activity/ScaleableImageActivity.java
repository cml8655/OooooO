package com.cml.second.app.baby.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.widget.PinchImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cmlBeliever on 2016/8/22.
 */
public class ScaleableImageActivity extends Activity {

    @Bind(R.id.imagescale_bg)
    View bgView;

    @Bind(R.id.imagescale_img)
    PinchImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        ButterKnife.bind(this);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.imagescale_exit);
    }

    @Override
    public void onBackPressed() {
        bgView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        super.onBackPressed();
    }
}
