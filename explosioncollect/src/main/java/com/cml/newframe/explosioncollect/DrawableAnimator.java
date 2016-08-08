package com.cml.newframe.explosioncollect;

import android.graphics.Canvas;

/**
 * Created by cmlBeliever on 2016/8/4.
 */
public interface DrawableAnimator {
    /**
     * @param canvas
     * @return true 已消费次绘图
     */
    boolean draw(Canvas canvas);
}
