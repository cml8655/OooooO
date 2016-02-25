package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.view.View;

import com.cml.second.app.baby.R;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class ShareFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        MenuActivity activity = (MenuActivity) getActivity();
//        LinearLayout container = activity.getToolbarCustomLayout();
//        container.removeAllViews();
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_share;
    }
}
