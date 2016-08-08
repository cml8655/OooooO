package com.cml.second.app.baby.fragment.dagger2.component;

import com.cml.second.app.baby.fragment.Dagger2Fragment;
import com.cml.second.app.baby.fragment.dagger2.modules.Dagger2Module;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cmlBeliever on 2016/8/8.
 */
@Component(modules = {Dagger2Module.class})
@Singleton
public interface Dagger2Component {

    void inject(Dagger2Fragment fragment);

}
