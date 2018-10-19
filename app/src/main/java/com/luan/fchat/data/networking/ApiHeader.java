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

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.luan.fchat.model.user.ParentProfile;
import com.luan.fchat.utils.SecurityUtils;

public class ApiHeader {

    private static ApiHeader mInstance;
    private MCPublicAPIHeader publicAPIHeader;
    private MCAPIHeader mPrivateApiHeader;

    public static ApiHeader getInstance(ParentProfile user) {
        if (mInstance == null) {
            MCPublicAPIHeader publicAPIHeader = new MCPublicAPIHeader("TDUPcG4xrsApRKH@4zVKTBTBEcq9WW");
            MCAPIHeader mPrivateApiHeader = new MCAPIHeader("");
            if (user != null && !TextUtils.isEmpty(user.getToken())) {
                mPrivateApiHeader.setApiKey(user.getToken());
            }
            mInstance = new ApiHeader(publicAPIHeader, mPrivateApiHeader);
        }
        return mInstance;
    }

    ApiHeader(MCPublicAPIHeader publicAPIHeader, MCAPIHeader privateAPIHeader) {
        this.publicAPIHeader = publicAPIHeader;
        this.mPrivateApiHeader = privateAPIHeader;
    }

    MCAPIHeader getPublicAPIHeader() {
        return new MCAPIHeader(SecurityUtils.convertSha256(publicAPIHeader.getApiKey()));
    }

    MCAPIHeader getPublicAPIHeader(String... params) {
        StringBuilder key = new StringBuilder(publicAPIHeader.getApiKey());
        for (String param : params) {
            key.append(param);
        }
        return new MCAPIHeader(SecurityUtils.convertSha256(key.toString()));
    }

    MCPublicAPIHeader getLoginHeader(String user, String pw) {
        return new MCPublicAPIHeader(SecurityUtils.convertSha256(publicAPIHeader.getApiKey() + user + pw));
    }

    MCAPIHeader getRegisterHeader(String username, String phone, String email, String password, String confirmPw) {
        return new MCAPIHeader(SecurityUtils.convertSha256(publicAPIHeader.getApiKey() + username + phone + email + password + confirmPw));
    }

    MCAPIHeader getPrivateAPIHeader() {
        return new MCAPIHeader(mPrivateApiHeader.getApiKey());
    }

    public void updatePrivateHeaderToken(String token) {
        if (mPrivateApiHeader != null) {
            mPrivateApiHeader.setApiKey(token);
        }
    }

    public static final class MCAPIHeader {

        @Expose
        @SerializedName("X-CSRF-Token")
        private String mApiKey;

        public MCAPIHeader(String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }

    public static final class MCPublicAPIHeader {
        @Expose
        @SerializedName("X-CSRF-Token")
        private String mApiKey;
        @Expose
        @SerializedName("platform")
        private String platform;

        public MCPublicAPIHeader(String apiKey) {
            mApiKey = apiKey;
            platform = "Android";
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }
}
