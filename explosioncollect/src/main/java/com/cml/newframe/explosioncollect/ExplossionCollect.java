package com.cml.newframe.explosioncollect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmlBeliever on 2016/8/2.
 * inspire from https://github.com/tyrantgit/ExplosionField/blob/master/explosionfield/src/main/java/tyrantgit/explosionfield/ExplosionField.java
 */
public class ExplossionCollect extends View {

    private List<ExplossionAnimator> explossionAnimators = new ArrayList<>();

    public ExplossionCollect(Context context) {
        super(context);
        this.init();
    }

    public ExplossionCollect(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public ExplossionCollect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (ExplossionAnimator anim : explossionAnimators) {
            anim.draw(canvas);
        }
    }


    public void start(final View fromView, View toView) {
        Bitmap bitmap = Utils.createBitmapFromView(fromView);

        final ExplossionAnimator animator = new ExplossionAnimator(this, bitmap, getLocationOnScreen(fromView), getLocationOnScreen(toView));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                explossionAnimators.remove(animator);
                fromView.setVisibility(VISIBLE);
            }
        });
        animator.setStartDelay(100);
        animator.setDuration(5000);

        explossionAnimators.add(animator);
        animator.start();
        fromView.setVisibility(GONE);
    }

    private PointF getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new PointF(location[0], location[1]);
    }

    /**
     * create ExplossionCollect and add to the root view
     *
     * @param activity
     * @return
     */
    public static ExplossionCollect createExplossionCollect(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        ExplossionCollect explosionField = new ExplossionCollect(activity);
        rootView.addView(explosionField, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return explosionField;
    }

    public static void removeExplossionCollect(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        int childCount = rootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = rootView.getChildAt(i);
            if (view instanceof ExplossionCollect) {
                rootView.removeView(view);
            }
        }
    }
}
