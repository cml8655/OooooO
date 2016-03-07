package com.cml.second.app.baby.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;
import com.cml.second.app.common.widget.WheelView;
import com.cml.second.app.common.widget.dialog.WheelViewDialog;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class ShareFragment extends BaseFragment {

    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};

    @Bind(R.id.wheel_view)
    WheelView wheelView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wheelView.setOffset(1);
        wheelView.setItems(Arrays.asList(PLANETS));
        wheelView.setSperatorStartRatio(0.2f);
        wheelView.setSperatorEndRatio(0.8f);
        wheelView.setSperatorColor(Color.RED);
        wheelView.setTextColor(Color.BLUE);
        wheelView.setSelectTextColor(Color.CYAN);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Toast.makeText(getContext(), "selectedIndex: " + selectedIndex + ", item: " + item, Toast.LENGTH_LONG).show();
            }
        });
//        MenuActivity activity = (MenuActivity) getActivity();
//        LinearLayout container = activity.getToolbarCustomLayout();
//        container.removeAllViews();
    }

    @OnClick(R.id.wheel_dialog)
    public void onWheelDialogClicked(View v) {

        WheelViewDialog dialog = new WheelViewDialog(getActivity(), new String[]{"男", "女"});
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setOnWheelSelectListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Toast.makeText(getContext(), "onWheelDialogClicked" + item, Toast.LENGTH_LONG).show();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_share;
    }
}
