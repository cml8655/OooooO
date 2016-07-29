package com.cml.second.app.baby;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.cml.second.app.common.crash.CrashHandler;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class AppApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        new CrashHandler().init(this);
        context = getApplicationContext();
        PlatformConfig.setQQZone("1105212352", "qC7NS39r8wIHfSkt");
        initDb("test");
    }

    public void initDb(String database) {
        //动态设置db名字
        Configuration configuration = new Configuration.Builder(this).setDatabaseName(database).create();
        ActiveAndroid.setLoggingEnabled(true);
        ActiveAndroid.initialize(configuration);
//        ActiveAndroid.initialize(this);
    }
}
