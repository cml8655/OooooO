package com.cml.second.app.baby.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.fragment.BaseFragment;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class ContainerActivity extends BaseSwipeBackActivity {

    private static final String EXTRA_CONTAINER = "ContainerActivity.EXTRA_CONTAINER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupCustomNavigation();
        setCustomTitle("测试");

        String targetClassName = getIntent().getStringExtra(EXTRA_CONTAINER);
        if (null != targetClassName) {
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, Fragment.instantiate(this, targetClassName)).commit();
        }

    }

    public static final void startActivity(Activity activity, Class<? extends BaseFragment> target) {
        Intent intent = new Intent(activity, ContainerActivity.class);
        intent.putExtra(EXTRA_CONTAINER, target.getName());
        activity.startActivity(intent);
    }

}
