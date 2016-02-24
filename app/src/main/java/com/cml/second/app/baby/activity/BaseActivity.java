package com.cml.second.app.baby.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.cml.second.app.baby.R;

/**
 * Created by cmlBeliever on 2016/2/23.
 */
public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private TextView titleView;

    protected int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);
        Log.e("dbw", "Status height:" + height);
        return height;
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleView = (TextView) findViewById(R.id.toolbar_title);
    }


    public void setCustomTitle(int titleRes) {
        titleView.setText(titleRes);
    }

    public void setCustomTitle(CharSequence title) {
        titleView.setText(title);
    }
}
