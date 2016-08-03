package com.cml.second.app.baby.widget.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;

import com.cml.second.app.baby.widget.MatrixImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cmlBeliever on 2016/8/3.
 */
public class ImageLoader {
    public static ImageLoader imageLoader = new ImageLoader();

    public static ImageLoader getInstance() {
        return imageLoader;
    }

    private String bastPath;

    public void init(String bastPath) {
        this.bastPath = bastPath;
        File file = new File(bastPath);
        if (!file.exists())
            file.mkdirs();
    }

    public void loadImg(String url, MatrixImageView imageView) {
        BitmapResult result = new BitmapResult();
        result.url = url;
        result.targetView = new WeakReference<>(imageView);

        if (imageView.getMeasuredWidth() == 0 || imageView.getMeasuredHeight() == 0) {
            DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
            result.viewWidth = displayMetrics.widthPixels;
            result.viewHeight = displayMetrics.heightPixels;
        } else {
            result.viewWidth = imageView.getMeasuredWidth();
            result.viewHeight = imageView.getMeasuredHeight();
        }

        new LoaderTask().execute(result);
    }

    private static class BitmapResult {
        String file;
        String url;
        int sample;
        int width;
        int height;
        Bitmap bitmap;
        int viewWidth;
        int viewHeight;
        WeakReference<MatrixImageView> targetView;

        @Override
        public String toString() {
            return "BitmapResult{" +
                    "viewHeight=" + viewHeight +
                    ", viewWidth=" + viewWidth +
                    ", height=" + height +
                    ", width=" + width +
                    ", sample=" + sample +
                    ", url='" + url + '\'' +
                    ", file='" + file + '\'' +
                    '}';
        }
    }

    private class LoaderTask extends AsyncTask<BitmapResult, Void, BitmapResult> {

        @Override
        protected BitmapResult doInBackground(BitmapResult... params) {
            BitmapResult result = params[0];
            result.file = bastPath + "/" + result.url.hashCode() + ".jpg";

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(result.url).openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(result.file)));

                byte[] data = new byte[2048];
                int len;
                while (-1 != (len = bufferedInputStream.read(data))) {
                    outputStream.write(data, 0, len);
                }

                inputStream.close();
                outputStream.close();

                return loadBitmap(result);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(BitmapResult result) {
            if (null != result) {

                Log.i("imageLoader", result.toString());
                Log.i("imageLoader size:", result.bitmap.getByteCount() / 1024 + "kb");

                if (null != result.targetView.get()) {
                    MatrixImageView imageView = result.targetView.get();
                    imageView.setImageBitmap(result.bitmap);
                    imageView.setmMaxScale(result.sample);
                }
            }
        }

        private BitmapResult loadBitmap(BitmapResult result) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(result.file, options);
            result.width = options.outWidth;
            result.height = options.outHeight;

            if (result.width < result.viewWidth && result.height < result.viewHeight) {
                options.inSampleSize = 1;
            } else {
                options.inSampleSize = (int) Math.max(result.width / (float) result.viewWidth, result.height / (float) result.viewHeight);
            }
            result.sample = options.inSampleSize;
            options.inJustDecodeBounds = false;
            result.bitmap = BitmapFactory.decodeFile(result.file, options);
            return result;
        }
    }
}
