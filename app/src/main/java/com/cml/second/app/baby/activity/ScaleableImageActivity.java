package com.cml.second.app.baby.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

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

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_image_scale);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
//                WindowManager.LayoutParams params = (WindowManager.LayoutParams) getWindow().getDecorView().getLayoutParams();
//                params.screenBrightness = 0.8f;
//                getWindow().setAttributes(params);
            }
        });

        ButterKnife.bind(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "changed:" + newConfig.orientation, Toast.LENGTH_LONG).show();
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
