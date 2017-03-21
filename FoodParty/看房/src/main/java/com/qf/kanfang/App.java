package com.qf.kanfang;

import android.app.Application;

/**
 * Created by Administrator on 2017/1/16.
 */
public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp() {
        return app;
    }

}
