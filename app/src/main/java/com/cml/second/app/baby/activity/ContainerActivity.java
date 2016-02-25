package com.cml.second.app.baby.activity;

import android.os.Bundle;

import com.cml.second.app.baby.R;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class ContainerActivity extends BaseSwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupCustomNavigation();
        setCustomTitle("测试");

    }

}
