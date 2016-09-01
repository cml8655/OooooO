package com.cml.second.app.baby.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cml.second.app.baby.R;

/**
 * Created by cmlBeliever on 2016/9/1.
 */
public class NestScrollingView extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = NestScrollingView.class.getSimpleName();

    private View headerView;
    private int headerHeight;

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

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i(TAG, "onStartNestedScroll===>nestedScrollAxes:" + nestedScrollAxes + "," + (nestedScrollAxes == View.SCROLL_AXIS_VERTICAL));
        //拦截上下滑动
        return nestedScrollAxes == View.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.i(TAG, "onStartNestedScroll===>dx:" + dx + ",dy:" + dy + ",consumed:" + consumed + "," + getScrollY());
        //该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，就通过最后一个参数consumed进行指定，例如我要消耗一半的dy，就可以写consumed[1]=dy/2

        boolean hiddenTop = dy > 0 && getScrollY() < headerHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i(TAG, "onNestedPreFling===>velocityX:" + velocityX + ",velocityY:" + velocityY);
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > headerHeight) {
            y = headerHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    private RecyclerView recyclerView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = findViewById(R.id.header);
        recyclerView = (RecyclerView) findViewById(R.id.nestsrolling_recycler);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        headerHeight = headerView.getMeasuredHeight();

        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = getMeasuredHeight() - headerHeight;

        setMeasuredDimension(getMeasuredWidth(),headerHeight+getChildAt(0).getMeasuredHeight()+recyclerView.getMeasuredHeight());

        Log.i("headerViewHeightonMeasure", "headerViewHeightonMeasure:" + headerHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("headerViewHeightonMeasure", "onSizeChanged:" + headerHeight);
    }
}
