package com.luan.fchat.utils;

import android.os.Environment;

/**
 * Created by tuan.giao on 11/8/2017.
 */

public class AppConstants {
    private static String externalStoragePatch = Environment
            .getExternalStorageDirectory().getPath();
    public static final int EPG_PAGING_NUM = 10;

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
    public static final int API_STATUS_CODE_SUCCESS = 200;
    public static final int API_STATUS_CODE_BAD_REQUEST = 400;
    public static final int API_STATUS_CODE_NOT_AUTHORIZED = 403;

    public static final int MINIMUM_CHARACTER_PARENT_PHONE_NUMBER = 8;
    public static final int MAXIMUM_CHARACTER_PARENT_PHONE_NUMBER = 13;

    private static final String EXTERNAL_FOLDER_SAVE_LANGUAGE_PACKAGE_NAME = externalStoragePatch + "/download";
    public static final String EXTERNAL_FOLDER_SAVE_LANGUAGE_PACKAGE = EXTERNAL_FOLDER_SAVE_LANGUAGE_PACKAGE_NAME + "/";
}
