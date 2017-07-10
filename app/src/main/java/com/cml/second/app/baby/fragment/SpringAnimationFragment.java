package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by cmlBeliever on 2017/7/10.
 */

public class SpringAnimationFragment extends BaseFragment {

    @Bind(R.id.navigation)
    android.support.design.widget.BottomNavigationView navigationView;

    @Bind(R.id.view1)
    View animView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationViewHelper.disableShiftMode(navigationView);
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Toast.makeText(getContext(), "menuClicked:" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
    }

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

    public static class BottomNavigationViewHelper {
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }
}
