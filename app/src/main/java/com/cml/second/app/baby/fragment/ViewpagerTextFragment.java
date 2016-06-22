package com.cml.second.app.baby.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cml.second.app.baby.R;

import butterknife.Bind;

/**
 * Created by cmlBeliever on 2016/6/22.
 */
public class ViewpagerTextFragment extends BaseFragment {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(20);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {

                TextView textView = new TextView(getActivity());
                textView.setText("vvvvvvvvvv" + position);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(Color.CYAN);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                container.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "点击事件：" + position, Toast.LENGTH_LONG).show();
                    }
                });
                return textView;
            }

            @Override
            public float getPageWidth(int position) {
                return 0.8f;
            }
        });


    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_viewpager_test;
    }
}
