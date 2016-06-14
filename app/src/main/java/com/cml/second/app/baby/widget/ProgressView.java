package com.cml.second.app.baby.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cmlBeliever on 2016/6/13.
 */
public class ProgressView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress = 0.6f;
    private int bgColor = Color.BLUE;
    private int progressColor = Color.RED;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.reset();

        //画两边的椭圆
        int radius = getHeight() / 2;
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);

        //画完成的
        paint.setColor(progressColor);
        rectF.set(0, 0, getWidth() * progress, getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }


    public void setProgress(float progress, int bgColor, int progressColor) {
        this.progress = progress;
        this.bgColor = bgColor;
        this.progressColor = progressColor;
        invalidate();
    }


}
