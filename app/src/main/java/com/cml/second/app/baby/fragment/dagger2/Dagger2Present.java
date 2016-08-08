package com.cml.second.app.baby.fragment.dagger2;

import android.widget.Toast;

import javax.inject.Singleton;

/**
 * Created by cmlBeliever on 2016/8/8.
 */
@Singleton
public class Dagger2Present implements Dagger2FragmentContract.Present {
    private Dagger2FragmentContract.View view;

    @Override
    public void injectView(Dagger2FragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void showMsg(String str) {
        Toast.makeText(view.getContext(), str, Toast.LENGTH_LONG).show();
    }
}
