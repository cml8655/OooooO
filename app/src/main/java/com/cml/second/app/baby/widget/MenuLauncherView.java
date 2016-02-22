package com.cml.second.app.baby.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cmlBeliever on 2016/2/22.
 */
public class MenuLauncherView extends View {

    private int launcherPadGap = 100;

    /**
     * 发射平台颜色
     */
    private int launcherPadColor;
    private Paint launcherPadPaint;
    /**
     * 发射物体颜色
     */
    private int launcherViewColor;
    private Paint launcherViewPaint;

    public MenuLauncherView(Context context) {
        super(context);
        init();
    }

    public MenuLauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuLauncherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MenuLauncherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //发射台颜色设置
        launcherPadColor = Color.GREEN;
        launcherPadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        launcherPadPaint.setColor(launcherPadColor);

        //发射物体颜色设置
        launcherViewColor = Color.RED;
        launcherViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        launcherViewPaint.setColor(launcherViewColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();


        canvas.drawRect(0, height - 10, width, height, launcherPadPaint);

        canvas.drawCircle(width / 2, height / 2, 20, launcherViewPaint);

    }
}
