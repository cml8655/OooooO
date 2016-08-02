package com.cml.newframe.explosioncollect;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by cmlBeliever on 2016/8/2.
 */
public class ExplossionAnimator extends ValueAnimator {

    private static AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator(0.8f);
    private static final int PARTICLE_SIZE = 15;

    private View container;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PointF targetLocation;
    private PointF fromLocation;
    private float distanceX;
    private float distanceY;

    private Particle[] particles;

    public ExplossionAnimator(View container, Bitmap bitmap, PointF fromLocation, PointF targetLocation) {
        this.container = container;
        this.bitmap = bitmap;
        this.targetLocation = targetLocation;
        this.fromLocation = fromLocation;

        distanceX = targetLocation.x - fromLocation.x;
        distanceY = targetLocation.y - fromLocation.y;

        setFloatValues(0, 100);
        setInterpolator(accelerateInterpolator);

        int w = bitmap.getWidth() / (PARTICLE_SIZE + 2);
        int h = bitmap.getHeight() / (PARTICLE_SIZE + 2);

        particles = new Particle[PARTICLE_SIZE * PARTICLE_SIZE];

        for (int i = 0; i < PARTICLE_SIZE; i++) {
            for (int j = 0; j < PARTICLE_SIZE; j++) {
                int color = bitmap.getPixel((j + 1) * w, (i + 1) * h);//获取当前位置的颜色值

            }
        }
    }

    private void generateParticle() {
    }

    @Override
    public void start() {
        super.start();
        container.invalidate();
    }

    public void draw(Canvas canvas) {
        if (!isStarted()) {
            return;
        }

//        Bitmap tempBitmap = Bitmap.createBitmap(bitmap, 0, 0, 20, 20);
        PointF point = calculatePosition();
        canvas.drawBitmap(bitmap, point.x, point.y, paint);
        container.postInvalidate();
    }

    private PointF calculatePosition() {
        float value = getAnimatedFraction();
        PointF point = new PointF();
        point.x = fromLocation.x + value * distanceX;
        point.y = fromLocation.y + value * distanceY;
        return point;
    }


    private class Particle {
        float x;
        float y;
        float alpha;
        float radius;
    }


}
