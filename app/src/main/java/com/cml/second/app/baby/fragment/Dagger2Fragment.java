package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.fragment.dagger2.Dagger2FragmentContract;
import com.cml.second.app.baby.fragment.dagger2.Dagger2Present;
import com.cml.second.app.baby.fragment.dagger2.component.DaggerDagger2Component;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/8/8.
 */
public class Dagger2Fragment extends BaseFragment implements Dagger2FragmentContract.View {

    @Inject
    Dagger2Present present;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDagger2Component.builder().build().inject(this);
        present.injectView(this);
    }

    @OnClick({R.id.dagger2_toast_btn})
    public void onClick(View view) {
        present.showMsg("点击事件");
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_dagger2;
    }
}
