package com.cml.second.app.baby.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.widget.MatrixImageView;

import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;

/**
 * Created by cmlBeliever on 2016/8/2.
 */
public class ImageViewFragment extends BaseFragment {

    String path = "http://imgcno0.nos.netease.com/img/R1N2VWIyZFFQMzNXVW4rNTBWWWtzQXZRN3A4UUh2Uk03dWxqZlRCS0hYcW5Gem9oZ2dsaUZnPT0.jpg?watermark&type=2&fontsize=1980&dissolve=70&stripmeta=0&font=bXN5aA==&text=wqljaHVueXU=";

    @Bind(R.id.img)
    MatrixImageView imageView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ImageLoader().execute();
    }

    private class ImageLoader extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                return BitmapFactory.decodeStream(connection.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(aVoid);
        }
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_matrix_img;
    }
}
