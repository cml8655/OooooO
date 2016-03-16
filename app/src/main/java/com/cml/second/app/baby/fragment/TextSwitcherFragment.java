package com.cml.second.app.baby.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.cml.second.app.baby.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * Created by cmlBeliever on 2016/3/16.
 */
public class TextSwitcherFragment extends BaseFragment {

    @Bind(R.id.text_switcher)
    TextSwitcher switcher;

    int i;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(22f);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLUE);
                return textView;
            }
        });
//        switcher.setInAnimation(loadEnterAnim());
//        switcher.setOutAnimation(loadExitAnim());
        switcher.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_textswitcher));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_textswitcher_exit));
        switcher.setText("测试");

        new Timer("dddd").schedule(new TimerTask() {
            @Override
            public void run() {
                final String text = "text" + i + "," + Thread.currentThread().getId();
                i++;
                switcher.post(new Runnable() {
                    @Override
                    public void run() {
                        switcher.setText(text);
                    }
                });
            }
        }, 1000, 2000);

    }

    private Animation loadExitAnim() {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1.0f);
        animation.setDuration(1000);
        return animation;
    }

    private Animation loadEnterAnim() {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f);
        animation.setDuration(1000);
        return animation;
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_textswitcher;
    }
}
