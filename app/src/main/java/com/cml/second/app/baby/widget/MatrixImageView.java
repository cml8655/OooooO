package com.cml.second.app.baby.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;


public class MatrixImageView extends ImageView {
    public final static String TAG = "MatrixImageView";
    private GestureDetector mGestureDetector;
    private Matrix mMatrix = new Matrix();
    private float mImageWidth;
    private float mImageHeight;
    private float mScale;
    float mMaxScale = 2;
    int bmHeight, bmWidth, width, height;
    private OnMovingListener moveListener;
    private OnSingleTapListener singleTapListener;
    Context mContext;

    private boolean hasLoadedBitmap;

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        MatrixTouchListener mListener = new MatrixTouchListener();
        setOnTouchListener(mListener);
        mGestureDetector = new GestureDetector(getContext(), new GestureListener(mListener));
        setBackgroundColor(Color.BLACK);
        setScaleType(ScaleType.CENTER_INSIDE);
    }

    public MatrixImageView(Context context) {
        super(context, null);
        mContext = context;
        MatrixTouchListener mListener = new MatrixTouchListener();
        setOnTouchListener(mListener);
        mGestureDetector = new GestureDetector(getContext(), new GestureListener(mListener));
        setBackgroundColor(Color.BLACK);
        setScaleType(ScaleType.CENTER_INSIDE);
    }

    public void setOnMovingListener(OnMovingListener listener) {
        moveListener = listener;
    }

    public void setOnSingleTapListener(OnSingleTapListener onSingleTapListener) {
        this.singleTapListener = onSingleTapListener;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        // TODO Auto-generated method stub
        super.setImageBitmap(bm);
        bmWidth = bm.getWidth();
        bmHeight = bm.getHeight();
        calculateMaxScale();
        Log.e("maxScale", "" + mMaxScale);
        Log.e("bmWidth", "" + bm.getWidth());
        Log.e("bmHeight", "" + bm.getHeight());
        if (getWidth() == 0) {
            ViewTreeObserver vto = getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    initData();
                    MatrixImageView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        } else {
            initData();
        }

        hasLoadedBitmap = true;
    }

    private void calculateMaxScale() {
        WindowManager wm = (WindowManager) mContext.getApplicationContext().getSystemService(mContext.WINDOW_SERVICE);
        Rect rect = new Rect();
        wm.getDefaultDisplay().getRectSize(rect);
        width = rect.width();
        height = rect.height();
        if (bmWidth > width || bmHeight > height) {
            mMaxScale = Math.max((float) bmWidth / (float) width, (float) bmHeight / (float) height);
        } else {
            mMaxScale = 1.0f;
        }
    }

    public void setmMaxScale(float mMaxScale) {
        this.mMaxScale = mMaxScale;
    }

    private void initData() {
        mMatrix.set(getImageMatrix());
        float[] values = new float[9];
        mMatrix.getValues(values);
        mImageWidth = getWidth() / values[Matrix.MSCALE_X];
        mImageHeight = (getHeight() - values[Matrix.MTRANS_Y] * 2) / values[Matrix.MSCALE_Y];
        mScale = values[Matrix.MSCALE_X];
    }

    public class MatrixTouchListener implements OnTouchListener {

        private static final int MODE_DRAG = 1;

        private static final int MODE_ZOOM = 2;

        private static final int MODE_UNABLE = 3;

        float mDobleClickScale = 2;
        private int mMode = 0;//

        private float mStartDis;

        private Matrix mCurrentMatrix = new Matrix();


        boolean mLeftDragable;
        boolean mRightDragable;

        boolean mFirstMove = false;
        private PointF mStartPoint = new PointF();

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (!hasLoadedBitmap) {
                return false;
            }

            // TODO Auto-generated method stub
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mMode = MODE_DRAG;
                    mStartPoint.set(event.getX(), event.getY());
                    isMatrixEnable();
                    startDrag();
                    checkDragable();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    reSetMatrix();
                    stopDrag();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mMode == MODE_ZOOM) {
                        setZoomMatrix(event);
                    } else if (mMode == MODE_DRAG) {
                        setDragMatrix(event);
                    } else {
                        stopDrag();
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (mMode == MODE_UNABLE) return true;
                    mMode = MODE_ZOOM;
                    mStartDis = distance(event);
                    break;
                case MotionEvent.ACTION_POINTER_UP:

                    break;
                default:
                    break;
            }
            return mGestureDetector.onTouchEvent(event);
        }


        private void startDrag() {
            if (moveListener != null) moveListener.startDrag();

        }


        private void stopDrag() {
            if (moveListener != null) moveListener.stopDrag();
        }


        private void checkDragable() {
            mLeftDragable = true;
            mRightDragable = true;
            mFirstMove = true;
            float[] values = new float[9];
            getImageMatrix().getValues(values);
            if (values[Matrix.MTRANS_X] >= 0)
                mRightDragable = false;
            if ((mImageWidth) * values[Matrix.MSCALE_X] + values[Matrix.MTRANS_X] <= getWidth()) {
                mLeftDragable = false;
            }
        }


        public void setDragMatrix(MotionEvent event) {
            if (isZoomChanged()) {
                float dx = event.getX() - mStartPoint.x;
                float dy = event.getY() - mStartPoint.y;
                if (Math.sqrt(dx * dx + dy * dy) > 10f) {
                    mStartPoint.set(event.getX(), event.getY());
                    mCurrentMatrix.set(getImageMatrix());
                    float[] values = new float[9];
                    mCurrentMatrix.getValues(values);
                    dy = checkDyBound(values, dy);
                    if (bmWidth > width) {
                        dx = checkDxBound(values, dx);
                    }
                    mCurrentMatrix.postTranslate(dx, dy);
                    setImageMatrix(mCurrentMatrix);
                }
            } else {
                stopDrag();
            }
        }


        private boolean isZoomChanged() {
            float[] values = new float[9];
            getImageMatrix().getValues(values);
            float scale = values[Matrix.MSCALE_X];
            return scale != mScale;
        }


        private float checkDyBound(float[] values, float dy) {
            float height = getHeight();
            if (mImageHeight * values[Matrix.MSCALE_Y] < height)
                return 0;
            if (values[Matrix.MTRANS_Y] + dy > 0)
                dy = -values[Matrix.MTRANS_Y];
            else if (values[Matrix.MTRANS_Y] + dy < -(mImageHeight * values[Matrix.MSCALE_Y] - height))
                dy = -(mImageHeight * values[Matrix.MSCALE_Y] - height) - values[Matrix.MTRANS_Y];
            return dy;
        }


        /**
         * 和当前矩阵对比，检验dx，使图像移动后不会超出ImageView边界
         *
         * @param values
         * @param dx
         * @return
         */
        private float checkDxBound(float[] values, float dx) {
            float width = getWidth();
            if (mImageWidth * values[Matrix.MSCALE_X] < width)
                return 0;
            if (values[Matrix.MTRANS_X] + dx > 0)
                dx = -values[Matrix.MTRANS_X];
            else if (values[Matrix.MTRANS_X] + dx < -(mImageWidth * values[Matrix.MSCALE_X] - width))
                dx = -(mImageWidth * values[Matrix.MSCALE_X] - width) - values[Matrix.MTRANS_X];
            return dx;
        }


        private void setZoomMatrix(MotionEvent event) {
            if (event.getPointerCount() < 2) return;
            float endDis = distance(event);
            if (endDis > 10f) {
                float scale = endDis / mStartDis;
                mStartDis = endDis;
                mCurrentMatrix.set(getImageMatrix());
                float[] values = new float[9];
                mCurrentMatrix.getValues(values);
//                scale = checkMaxScale(scale, values);
                PointF centerF = getCenter(scale, values);
                mCurrentMatrix.postScale(scale, scale, centerF.x, centerF.y);
                setImageMatrix(mCurrentMatrix);
            }
        }


        private PointF getCenter(float scale, float[] values) {
            if (scale * values[Matrix.MSCALE_X] < mScale || scale >= 1) {
                return new PointF(getWidth() / 2, getHeight() / 2);
            }
            float cx = getWidth() / 2;
            float cy = getHeight() / 2;
//            if ((getWidth() / 2 - values[Matrix.MTRANS_X]) * scale < getWidth() / 2)
//                cx = 0;
//            if ((mImageWidth * values[Matrix.MSCALE_X] + values[Matrix.MTRANS_X]) * scale < getWidth())
//                cx = getWidth();
            return new PointF(cx, cy);
        }


        private float checkMaxScale(float scale, float[] values) {
            Log.e("mMaxScale", "" + mMaxScale);
            Log.e("scale____", "" + values[Matrix.MSCALE_X]);
            if (values[Matrix.MSCALE_X] >= 1) {
                if (scale < 1) {
                    return scale;
                } else {
                    scale = 1;
                }
            }
            return scale;
        }


        private void reSetMatrix() {
            if (checkRest()) {
                mCurrentMatrix.set(mMatrix);
                setImageMatrix(mCurrentMatrix);
            } else {
                float[] values = new float[9];
                getImageMatrix().getValues(values);

                float scale = values[Matrix.MSCALE_X];

                //TODO
                if (scale > mMaxScale) {
                    PointF centerF = getCenter(mMaxScale / scale, values);
                    mCurrentMatrix.postScale(mMaxScale / scale, mMaxScale / scale, centerF.x, centerF.y);
                    setImageMatrix(mCurrentMatrix);
                }


                float height = mImageHeight * values[Matrix.MSCALE_Y];
                if (height < getHeight()) {
                    float topMargin = (getHeight() - height) / 2;
                    if (topMargin != values[Matrix.MTRANS_Y]) {
                        mCurrentMatrix.set(getImageMatrix());
                        mCurrentMatrix.postTranslate(0, topMargin - values[Matrix.MTRANS_Y]);
                        setImageMatrix(mCurrentMatrix);
                    }
                }
            }
        }


        private boolean checkRest() {
            float[] values = new float[9];
            getImageMatrix().getValues(values);
            float scale = values[Matrix.MSCALE_X];
            return scale < mScale;
        }


        private void isMatrixEnable() {
            if (getScaleType() != ScaleType.CENTER) {
                setScaleType(ScaleType.MATRIX);
            } else {
                mMode = MODE_UNABLE;
            }
        }

        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        public void onDoubleClick() {
            float scale = isZoomChanged() ? 1 : mMaxScale;
            mCurrentMatrix.set(mMatrix);
            mCurrentMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mCurrentMatrix);
        }
    }


    private class GestureListener extends SimpleOnGestureListener {
        private final MatrixTouchListener listener;

        public GestureListener(MatrixTouchListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
//            listener.onDoubleClick();
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
            super.onShowPress(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            // TODO Auto-generated method stub
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (singleTapListener != null) singleTapListener.onSingleTap();
            return super.onSingleTapConfirmed(e);
        }

    }

    public interface OnMovingListener {
        public void startDrag();

        public void stopDrag();
    }


    public interface OnSingleTapListener {
        public void onSingleTap();
    }
}
