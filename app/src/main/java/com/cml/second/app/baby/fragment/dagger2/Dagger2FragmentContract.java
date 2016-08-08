package com.cml.second.app.baby.fragment.dagger2;

import android.content.Context;

/**
 * Created by cmlBeliever on 2016/8/8.
 */
public interface Dagger2FragmentContract {
    interface View {
        Context getContext();
    }

    interface Present {
        void injectView(View view);
        void showMsg(String str);
    }
}
