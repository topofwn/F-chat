/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.luan.fchat.data.networking;

import com.luan.fchat.model.APIResponse;
import com.luan.fchat.utils.AppConstants;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;

/**
 * Created by tuan.giao on 11/8/2017.
 */

public class AppApiHelper implements ApiHelper {

    private static AppApiHelper mInstance;
    private static ApiHeader mApiHeader;

    public static AppApiHelper getInstance(ApiHeader apiHeader) {
        if (mInstance == null) {
            mInstance = new AppApiHelper();
            mApiHeader = apiHeader;
        }
        return mInstance;
    }

    private AppApiHelper() {
    }


    @Override
    public void updatePrivateToken(String token) {
        mApiHeader.updatePrivateHeaderToken(token);
    }

    @Override
    public Observable<String> getLanguageFileFromServer(String url) {
        return Rx2AndroidNetworking.download(url, AppConstants.EXTERNAL_FOLDER_SAVE_LANGUAGE_PACKAGE, "android_lang.zip")
                .build()
                .getDownloadObservable();
    }

    @Override
    public Observable<APIResponse> login(String user, String pw, String tuneId) {
        return null;
    }
}

