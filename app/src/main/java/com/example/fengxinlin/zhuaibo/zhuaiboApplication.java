package com.example.fengxinlin.zhuaibo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by fengxinlin on 9/27/16.
 */
public class zhuaiboApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
