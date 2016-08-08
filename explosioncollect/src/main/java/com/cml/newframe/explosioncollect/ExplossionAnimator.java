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
public class ExplossionAnimator extends ValueAnimator implements DrawableAnimator {

    private static AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator(0.8f);
    private static final int PARTICLE_SIZE = 10;
    private static final int EXPLOSSION_X = 20;
    private static final int EXPLOSSION_Y = 20;

    private View container;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PointF targetLocation;
    private PointF fromLocation;
    private float distanceX;
    private float distanceY;

    private Particle[] particles;

    private int w;
    private int h;

    public ExplossionAnimator(View container, Bitmap bitmap, PointF fromLocation, PointF targetLocation) {
        this.container = container;
        this.bitmap = bitmap;
        this.targetLocation = targetLocation;
        this.fromLocation = fromLocation;

        distanceX = targetLocation.x - fromLocation.x;
        distanceY = targetLocation.y - fromLocation.y;

        setFloatValues(0, 100);
        setInterpolator(accelerateInterpolator);

        w = bitmap.getWidth() / PARTICLE_SIZE;
        h = bitmap.getHeight() / PARTICLE_SIZE;

        int radius = Math.min(w, h) / 2;

        particles = new Particle[PARTICLE_SIZE * PARTICLE_SIZE];


        for (int i = 0; i < PARTICLE_SIZE; i++) {
            for (int j = 0; j < PARTICLE_SIZE; j++) {
                Particle particle = new Particle();
                particle.radius = (float) (radius * Math.random());
                particle.x = fromLocation.x + j * w;
                particle.transX = getTrans(j, EXPLOSSION_X) * (Math.abs(PARTICLE_SIZE / 2 - j));
                particle.y = fromLocation.y + i * h;
                particle.transY = getTrans(i, EXPLOSSION_Y) * (Math.abs(PARTICLE_SIZE / 2 - i));
                particle.bitmap = Bitmap.createBitmap(bitmap, j * w, i * h, w, h);
                particles[i * PARTICLE_SIZE + j] = particle;
            }
        }
    }

    private int getTrans(int current, int value) {
        if (current < PARTICLE_SIZE / 2) {
            return -value;
        }
        return value;
    }

    @Override
    public void start() {
        super.start();
        container.invalidate();
    }

    public boolean draw(Canvas canvas) {
        if (!isStarted()) {
            return false;
        }
        float value = getAnimatedFraction();

        for (int i = 0; i < particles.length; i++) {
            Particle particle = particles[i];
            canvas.drawBitmap(particle.bitmap, particle.x + value * particle.transX, particle.y + value * particle.transY, paint);
        }
        container.postInvalidate();
        return true;
    }


    private class Particle {
        float x;
        float y;
        float radius;
        Bitmap bitmap;
        float transX;
        float transY;
    }


}
