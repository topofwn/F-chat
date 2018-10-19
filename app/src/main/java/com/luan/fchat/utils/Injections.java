package com.luan.fchat.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import com.luan.fchat.data.DataManager;
import com.luan.fchat.data.DataManagerImpl;

/**
 * Created by tuan.giao on 11/8/2017.
 */

public class Injections {

    public static DataManager provideAppDataManager(@NonNull Context context) {
        return DataManagerImpl.getInstance(context);
    }

    public static SchedulerProvider provideSchedulerProvider() {
        return AppSchedulerProvider.getInstance();
    }
}
