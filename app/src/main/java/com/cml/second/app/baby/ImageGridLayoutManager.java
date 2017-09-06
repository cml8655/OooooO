package com.cml.second.app.baby;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cmlBeliever on 2017/7/21.
 */

public class ImageGridLayoutManager extends RecyclerView.LayoutManager {

    private static final String TAG = "ImageGridLayoutManager";

    private Context context;

    private int currentIndex = 1;
    private int childWidth;
    private int containerWidth;


    private int totalOffset;

    private OrientationHelper orientationHelper;

    public ImageGridLayoutManager(Context context) {
        this.context = context;
        containerWidth = context.getResources().getDisplayMetrics().widthPixels;
        childWidth = (containerWidth - getPaddingLeft() - getPaddingRight()) / 3;
        orientationHelper = OrientationHelper.createHorizontalHelper(this);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i(TAG, "onLayoutChildren");
        // 跳过preLayout，preLayout主要用于支持动画，暂时先使用自带的简单的fading
        if (state.isPreLayout()) {
            return;
        }
        removeAndRecycleAllViews(recycler);
        fill(0, recycler, state);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    /**
     * 根据位置画好数据
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    private int fill(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (state.isPreLayout()) {
            return dx;
        }

        detachAndScrapAttachedViews(recycler);

        int startIndex = calculateCurrentIndex(dx);
        if (startIndex >= state.getItemCount() - 3) {
            startIndex = state.getItemCount() - 3;
            dx = 0;
        }
        int endIndex = startIndex + 4;
        if (endIndex >= state.getItemCount() - 1) {
            endIndex = state.getItemCount() - 1;
            dx = 0;
        }
        int offsetStart;
        if (totalOffset > 0) {
            offsetStart = -totalOffset % childWidth;
        } else {
            offsetStart = childWidth - totalOffset % childWidth;
        }
        //初始化三个
        for (int i = startIndex; i < endIndex; i++) {
            // 根据position获取一个碎片view，可以从回收的view中获取，也可能新构造一个
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);
            // 计算此碎片view包含边距的尺寸
            measureChildWithMargins(scrap, 0, 0);

            // 获取此碎片view包含边距和装饰的宽度width
            int width = childWidth;
//            int width = getDecoratedMeasuredWidth(scrap);
            // 获取此碎片view包含边距和装饰的高度height
            int height = getDecoratedMeasuredHeight(scrap);
            int offsetY = 0;
            // Important！布局到RecyclerView容器中，所有的计算都是为了得出任意position的item的边界来布局
            layoutDecorated(scrap, offsetStart, offsetY, offsetStart + width, offsetY + height);
            offsetStart += width;
        }
        return dx;
    }

    protected int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }


    private int calculateCurrentIndex(int dx) {
        return Math.abs(totalOffset) / childWidth;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (dx == 0 || getChildCount() == 0) {
            return 0;
        }
        totalOffset += dx;
        dx = fill(dx, recycler, state);
        offsetChildrenHorizontal(-dx);
        return dx;
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        Log.i(TAG, "generateDefaultLayoutParams");
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
