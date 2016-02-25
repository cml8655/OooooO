package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cml.second.app.baby.R;
import com.cml.second.app.baby.activity.MenuActivity;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class MainFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MenuActivity activity = (MenuActivity) getActivity();
        LinearLayout container = activity.getToolbarCustomLayout();

        ImageView img = new ImageView(activity);
        img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Glide.with(this).load("http://pic11.nipic.com/20101107/5963073_141803043683_2.gif").asGif().placeholder(R.drawable.success_circle).into(img);
//        Glide.with(this).load("http://b.hiphotos.baidu.com/zhidao/pic/item/63d9f2d3572c11dfb068871a612762d0f703c249.jpg").into(img);
        container.addView(img);

//        <ImageView
//        android:id="@+id/img"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:fitsSystemWindows="true"
//        android:src="@mipmap/ic_launcher"/>

    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_main;
    }
}
