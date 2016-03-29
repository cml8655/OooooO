package com.cml.second.app.baby.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/3/29.<br>
 * peekHeight是当Bottom Sheets关闭的时候，底部下表我们能看到的高度，hideable 是当我们拖拽下拉的时候,bottom sheet是否能全部隐藏。
 */
public class BottomSheetActivity extends BaseRxActivity {
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bottom_sheet);

        ButterKnife.bind(this);
        behavior = BottomSheetBehavior.from(findViewById(R.id.scroll));

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });

    }

    @OnClick(R.id.show_bottom_sheet)
    public void show(View view) {
        Toast.makeText(this, "show bottom sheet" + behavior.getState(), Toast.LENGTH_SHORT).show();
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @OnClick(R.id.show_bottom_dialog)
    public void showBottomDialog(View view) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setTitle("bottom sheet");
        dialog.setContentView(R.layout.dialog_bottom_sheet);
        dialog.show();
    }


}
