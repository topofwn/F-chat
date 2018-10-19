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

import io.reactivex.Observable;


public interface ApiHelper {


    /**
     * update new token of user
     * @param token
     */
    void updatePrivateToken(String token);

    /**
     * download the language file from server
     * @param url
     * @return
     */
    Observable<String> getLanguageFileFromServer(String url);

    /**
     * login with user and pw
     * @param user
     * @param pw
     * @return
     */
    Observable<APIResponse> login(String user, String pw, String tuneId);
}
