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

public class ImageGridLayoutManager2 extends RecyclerView.LayoutManager {

    private static final String TAG = "ImageGridLayoutManager";

    private Context context;

    private int offsetX;
    private int currentIndex = 0;
    private int childWidth;
    private int containerWidth;


    private int totalOffset;

    private OrientationHelper orientationHelper;

    public ImageGridLayoutManager2(Context context) {
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
        // 分离所有的itemView
        detachAndScrapAttachedViews(recycler);
        offsetX = 0;
        int offsetY = 0;

        //初始化三个
        for (int i = 0; i < 3; i++) {
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
            if (i != 1) {
                height *= 0.6;
                offsetY = (int) (height * 0.4);
            } else {
                offsetY = 0;
            }

            // Important！布局到RecyclerView容器中，所有的计算都是为了得出任意position的item的边界来布局
            layoutDecorated(scrap, offsetX, offsetY, offsetX + width, offsetY + height);

            offsetX += width;
        }
        currentIndex = 2;
//        detachAndScrapAttachedViews(recycler);
//        this.offsetX += -childWidth / 2;
//        offsetChildrenHorizontal(-childWidth / 2);
    }

    private int getMaxOffsetX(RecyclerView.State state) {
        if (getChildCount() == 0)
            return 0;

        return state.getItemCount() * childWidth - context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (dx == 0 || getChildCount() == 0) {
            return 0;
        }
        totalOffset += dx;

        if (dx < 0) {
            if (offsetX + dx < 0) {
                dx = -offsetX;
            }
        } else {
            int maxOffsetX = getMaxOffsetX(state);
            if (offsetX + dx > maxOffsetX) {
                dx = maxOffsetX - offsetX;
            }
        }
        offsetX += dx;
        //计算view
        fillView(dx, recycler, state);
        offsetChildrenHorizontal(-dx);

        Log.i(TAG, "scrollHorizontallyBy:" + state);
        return dx;
    }

    //填充view
    protected void fillView(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (dx < 0 && currentIndex < state.getItemCount() - 1) {
//            boolean isNewView = needAddView(dx, recycler, state);
//            if (!isNewView)
//                return;
            View nextView = recycler.getViewForPosition(currentIndex + 1);
            addView(nextView);
            // 计算此碎片view包含边距的尺寸
            measureChildWithMargins(nextView, 0, 0);
            int height = getDecoratedMeasuredHeight(nextView);
            int offsetY = 0;

            int startX = getDecoratedRight(getChildAt(currentIndex));
            currentIndex++;
//            int startX = currentIndex * childWidth - totalOffset;
            layoutDecorated(nextView, startX, offsetY, startX + childWidth, offsetY + height);
        }

    }

    private boolean needAddView(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int maxOffset = getDecoratedRight(getChildAt(getChildCount() - 1)) - offsetX;
        return true;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        Log.i(TAG, "generateDefaultLayoutParams");
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
