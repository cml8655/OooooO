package com.cml.newframe.explosioncollect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cmlBeliever on 2016/8/2.
 */
public class Utils {
    public static Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap temp = view.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return bitmap;
    }
}
