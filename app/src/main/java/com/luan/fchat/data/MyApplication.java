package com.luan.fchat.data;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageController.newInstance(getApplicationContext());
    }


}
