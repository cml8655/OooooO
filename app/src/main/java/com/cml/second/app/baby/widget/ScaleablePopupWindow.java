package com.cml.second.app.baby.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.cml.second.app.baby.R;

/**
 * Created by cmlBeliever on 2016/8/22.
 */
public class ScaleablePopupWindow {
    private Context context;
    private PopupWindow window;

    private View bgView;
    private PinchImageView imageView;

    public ScaleablePopupWindow(Context context) {
        this.context = context;
    }

    public void create() {
        final View contentView = LayoutInflater.from(context).inflate(R.layout.activity_image_scale, null);

        imageView = (PinchImageView) contentView.findViewById(R.id.imagescale_img);
        bgView = contentView.findViewById(R.id.imagescale_bg);


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(context, "key", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        window.setAnimationStyle(R.style.PopupWindow);
    }


    public void show(View view, int offsetX, int offsetY) {
        window.showAtLocation(view, Gravity.LEFT, offsetX, offsetY);
    }

}
