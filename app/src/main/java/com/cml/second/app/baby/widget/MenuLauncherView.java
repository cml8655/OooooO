package com.cml.second.app.baby.widget;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cmlBeliever on 2016/2/22.
 */
public class MenuLauncherView extends View {

    private static final String TAG = MenuLauncherView.class.getSimpleName();

    private float launcherPadGap = 200;
    private boolean isLaunching;

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
    private PointF touchPoint;

    /**
     * 发射动画坐标
     */
    private PointF launchingPoint = new PointF();
    private ValueAnimator animator;

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
        launcherPadPaint.setStrokeWidth(10);
        launcherPadPaint.setStyle(Paint.Style.STROKE);

        //发射物体颜色设置
        launcherViewColor = Color.RED;
        launcherViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        launcherViewPaint.setColor(launcherViewColor);

        launcherPadGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, launcherPadGap, getContext().getResources().getDisplayMetrics());
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != animator && animator.isRunning()) {
            animator.removeAllUpdateListeners();
            animator.end();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLaunching) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchPoint = new PointF(event.getX(), event.getY());
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                launchingPoint.x = touchPoint.x;
                startLaunchAnim();
                touchPoint = null;
                return true;
        }

        return super.onTouchEvent(event);
    }

    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
//            float value = (float) animation.getAnimatedValue();
//            launchingPoint.y = value;
            PointF value = (PointF) animation.getAnimatedValue();
            launchingPoint = value;
            postInvalidate();
        }
    };


    private void startLaunchAnim() {
        animator = new ValueAnimator();
        animator.addUpdateListener(updateListener);
        animator.setDuration(250);
//        animator.setFloatValues(touchPoint.y, 0);
        animator.setObjectValues(touchPoint, new PointF(touchPoint.x, 0));
        animator.setEvaluator(new BezierEvaluator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isLaunching = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isLaunching = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isLaunching = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private double getAngle(PointF center) {

        //计算三条边长
        double lenLeft = Math.sqrt(Math.pow(center.x, 2) + Math.pow(center.y - (getMeasuredHeight() - launcherPadGap), 2));
        double lenRight = Math.sqrt(Math.pow(getMeasuredWidth() - center.x, 2) + Math.pow(center.y - (getMeasuredHeight() - launcherPadGap), 2));
        double lenCenter = getMeasuredWidth();

        double value = (lenLeft * lenLeft + lenLeft * lenRight - lenCenter * lenCenter) / 2 * lenLeft * lenRight;
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //折现

        if (!isLaunching) {
            if (touchPoint == null) {
                //初始化图片
                canvas.drawLine(0, height - launcherPadGap, width, height - launcherPadGap, launcherPadPaint);
            } else {
                //画跳绳
                canvas.drawLine(0, height - launcherPadGap, touchPoint.x, touchPoint.y, launcherPadPaint);
                canvas.drawLine(touchPoint.x, touchPoint.y, width, height - launcherPadGap, launcherPadPaint);
                //画发射器
                canvas.drawCircle(touchPoint.x, touchPoint.y, 100, launcherViewPaint);
            }

        } else {
            //画发射器
            canvas.drawCircle(launchingPoint.x, launchingPoint.y, 100, launcherViewPaint);
            if (launchingPoint.y <= height - launcherPadGap) {
                canvas.drawLine(0, height - launcherPadGap, width, height - launcherPadGap, launcherPadPaint);
            } else {
                //画跳绳
                canvas.drawLine(0, height - launcherPadGap, launchingPoint.x, launchingPoint.y, launcherPadPaint);
                canvas.drawLine(launchingPoint.x, launchingPoint.y, width, height - launcherPadGap, launcherPadPaint);
            }
        }


//        float r = 100;
//        for (int j = 0; j <= 360; j++) {
//            double x = r * Math.cos(Math.PI / 45 * j) + 320 / 2;
//            double y = -r * Math.sin(Math.PI / 45 * j) + 400 / 4;
//        }

//        drawLove(canvas);
//
//        float r = 100;
//        for (int i = 1; i <= 360; i++) {
//
//            float startX = (float) getX(r, Math.PI * i);
//            float startY = (float) getY(r, Math.PI * i);
//            i++;
//            float endX = (float) getX(r, Math.PI * i);
//            float endY = (float) getY(r, Math.PI * i);
//            canvas.drawLine(startX, startY, endX, endY, launcherPadPaint);
//        }
//

//        canvas.drawLine(0, height - launcherPadGap, touchPoint.x, touchPoint.y, launcherPadPaint);
//        canvas.drawLine(touchPoint.x, touchPoint.y, width, height - launcherPadGap, launcherPadPaint);


    }

    private void drawLove(Canvas canvas) {
        //(17*(x^2))-(16*abs(x)*y)+(17*(y^2))<255 x(-5,5) y(-5,5) (心形函数方程式)
        int loveWidth = 500;//心型宽度，必须是偶数
        int oneLine = loveWidth / 2;//一条轴长度

        float scale = oneLine / 5f;//实际坐标比上方程式坐标，倍数

        for (int i = 0; i < oneLine; i++) {
            for (int j = 0; j < oneLine; j++) {

                //根据表达式xy的范围，所以要把坐标系的范围也缩小
                float xf = i / scale;
                float yf = j / scale;

                if ((17 * Math.pow(xf, 2) - 16 * Math.abs(xf) * yf + 17 * Math.pow(yf, 2)) < 255) {

                    //右上1
//                  canvas.drawPoint(xf*scale+250,250+yf*scale, paint);
                    //左下2
                    canvas.drawPoint(250 - xf * scale, 250 - yf * scale, launcherPadPaint);
//                  this.postInvalidateDelayed(10);


                    //右上1
//                  canvas.drawPoint(-xf*scale+250,250+yf*scale, paint);
                    //右下2
                    canvas.drawPoint(250 + xf * scale, 250 - yf * scale, launcherPadPaint);
//                  this.postInvalidateDelayed(10);

//                  Log.i("TAG", "xf-->"+(xf*scale+250)+"yf-->"+(250-yf*scale));
                }

                if ((17 * Math.pow(xf, 2) - 16 * Math.abs(xf) * (-yf) + 17 * Math.pow(yf, 2)) < 255) {

                    //左上2
                    canvas.drawPoint(250 - xf * scale, 250 + yf * scale, launcherPadPaint);
                    //右下 1
//                  canvas.drawPoint(250+xf*scale,250-yf*scale, paint);

//                  this.postInvalidateDelayed(10);


                    //左上2
                    canvas.drawPoint(250 + xf * scale, 250 + yf * scale, launcherPadPaint);
                    //右下 1
//                  canvas.drawPoint(250-xf*scale,250-yf*scale, paint);

//                  this.postInvalidateDelayed(10);

                }
            }
        }//end for

    }

    private double getX(float r, double t) {//由弧度得到 X 坐标
        return 100 + r * (16 * Math.pow(Math.sin(t), 3));
    }

    private double getY(float r, double t) {//由弧度得到 Y 坐标
        return 50 - r * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t));
    }

    private void drawLauncherLine(Canvas canvas, int width, int height) {
        Path launchPadPath = new Path();
        launchPadPath.reset();
        launchPadPath.moveTo(0, height - launcherPadGap);
        launchPadPath.quadTo(touchPoint.x, touchPoint.y, width, height - launcherPadGap);
        canvas.drawPath(launchPadPath, launcherPadPaint);
    }

    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            final float t = fraction;
            float oneMinusT = 1.0f - t;
            PointF point = new PointF();


            PointF point1 = new PointF();
            point1.set(getWidth() * oneMinusT, 0);

            PointF point2 = new PointF();
            point2.set(0, getHeight() * oneMinusT);

            point.x = oneMinusT * oneMinusT * oneMinusT * (startValue.x)
                    + 3 * oneMinusT * oneMinusT * t * (point1.x)
                    + 3 * oneMinusT * t * t * (point2.x)
                    + t * t * t * (endValue.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (startValue.y)
                    + 3 * oneMinusT * oneMinusT * t * (point1.y)
                    + 3 * oneMinusT * t * t * (point2.y)
                    + t * t * t * (endValue.y);
            return point;
        }
    }
}
