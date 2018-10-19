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


final class ApiEndPoint {

    private static final String BASE_URL = "http://ams-api.astro.com.my/";

    static final String END_POINT_GET_CHANNEL_LIST = BASE_URL +  "ams/v3/getChannelList";
    static final String END_POINT_GET_EVENTS = BASE_URL +  "ams/v3/getEvents";

    static final String QUERY_PARAM_CHANNEL_ID = "channelId";
    static final String QUERY_PARAM_PERIOD_START = "periodStart";
    static final String QUERY_PARAM_PERIOD_END = "periodEnd";
}
