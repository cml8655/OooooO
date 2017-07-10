package com.cml.second.app.baby.fragment;

import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by cmlBeliever on 2017/7/10.
 */

public class SpringAnimationFragment extends BaseFragment {


    @Bind(R.id.view1)
    View animView;

    @OnClick(R.id.view1)
    public void onViewClieck() {
        Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.startBtn)
    public void startAnim(View view) {
        animView.setY(0);
        animView.setX(0);
        SpringForce force = new SpringForce(500).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_LOW);
        SpringAnimation animation = new SpringAnimation(animView, SpringAnimation.TRANSLATION_Y).setSpring(force);
        animation.start();

        SpringForce force2 = new SpringForce(500).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_LOW);
        SpringAnimation animation2 = new SpringAnimation(animView, SpringAnimation.TRANSLATION_X).setSpring(force2);
        animation2.start();

        SpringForce force3 = new SpringForce(3).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_LOW);
        SpringAnimation animation3 = new SpringAnimation(animView, SpringAnimation.SCALE_X).setSpring(force3);
        animation3.start();
    }

    @OnClick(R.id.startBtn2)
    public void startAnim2(View view) {
        animView.setScaleX(0);
        animView.setScaleY(0);
        SpringForce force2 = new SpringForce(2).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_LOW);
        SpringAnimation animation2 = new SpringAnimation(animView, SpringAnimation.SCALE_Y).setSpring(force2);
        animation2.start();

        SpringForce force3 = new SpringForce(2).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_LOW);
        SpringAnimation animation3 = new SpringAnimation(animView, SpringAnimation.SCALE_X).setSpring(force3);
        animation3.start();
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_spring_anim;
    }

    @Override
    public int getTitle() {
        return R.string.title_springanimnation;
    }
}
