package com.luan.fchat.utils;

import io.reactivex.Scheduler;

/**
 * Created by tuan.giao on 11/8/2017.
 */

public interface SchedulerProvider {
    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
