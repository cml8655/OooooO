package com.cml.second.app.baby.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.widget.PinchImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by cmlBeliever on 2016/8/22.
 */
public class ScaleableImageActivity extends Activity {

    @Bind(R.id.imagescale_bg)
    View bgView;

    @Bind(R.id.imagescale_img)
    PinchImageView imageView;

    @Bind(R.id.img2)
    ImageView imageView2;

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

        imageView2.post(new Runnable() {
            @Override
            public void run() {
                new PhotoViewAttacher(imageView2);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
