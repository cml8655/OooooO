package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;

import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/3/29.
 */
public class BottomSheetFragment extends BaseFragment {

    private BottomSheetBehavior behavior;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        behavior = BottomSheetBehavior.from(view.findViewById(R.id.scroll));
    }

    @OnClick(R.id.show_bottom_sheet)
    public void show(View view) {
        Toast.makeText(getContext(),"show bottom sheet",Toast.LENGTH_SHORT).show();
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    @Override
    protected int getContainerRes() {
        return R.layout.fragment_bottom_sheet;
    }
}
