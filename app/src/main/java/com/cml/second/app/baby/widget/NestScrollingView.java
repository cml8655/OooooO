package com.cml.second.app.baby.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by cmlBeliever on 2016/9/1.
 */
public class NestScrollingView extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = NestScrollingView.class.getSimpleName();

    public NestScrollingView(Context context) {
        super(context);
    }

    public NestScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestScrollingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(TAG, "onNestedFling===>velocityX:" + velocityX + ",velocityY:" + velocityY + ",consumed:" + consumed);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        Log.i(TAG, "onNestedPrePerformAccessibilityAction===>action:" + action + ",args:" + args);
        return super.onNestedPrePerformAccessibilityAction(target, action, args);
    }

    /**
     * 必须返回true进行拦截
     *
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i(TAG, "onNestedPrePerformAccessibilityAction===>nestedScrollAxes:" + nestedScrollAxes);
        return true;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i(TAG, "onNestedPreFling===>velocityX:" + velocityX + ",velocityY:" + velocityY);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
