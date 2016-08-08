package com.cml.second.app.baby.fragment.dagger2.modules;

import com.cml.second.app.baby.fragment.dagger2.Dagger2Present;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cmlBeliever on 2016/8/8.
 */
@Module
public class Dagger2Module {

    @Provides
    public Dagger2Present getPresent() {
        return new Dagger2Present();
    }
}
