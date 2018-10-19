package com.luan.fchat.data;

import android.content.Context;


import com.luan.fchat.data.networking.ApiHeader;
import com.luan.fchat.data.networking.ApiHelper;
import com.luan.fchat.data.networking.AppApiHelper;
import com.luan.fchat.data.snappydb.MySnappyImpl;
import com.luan.fchat.model.APIResponse;
import com.luan.fchat.model.user.ParentProfile;

import io.reactivex.Observable;

/**
 * Created by tuan.giao on 11/9/2017.
 */

public class DataManagerImpl implements DataManager {
    private static DataManagerImpl mInstance;

    private ApiHelper mApiHelper;
    private MySnappyImpl mySnappy;
    private ParentProfile mCurrentUser;


    public static DataManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataManagerImpl(context);
        }
        return mInstance;
    }

    private DataManagerImpl(Context context) {
        this.mySnappy = MySnappyImpl.newInstance(context);
        this.mCurrentUser = mySnappy.getCurrentUser();
        this.mApiHelper = AppApiHelper.getInstance(ApiHeader.getInstance(mCurrentUser));
    }

    @Override
    public void updatePrivateToken(String token) {
        mApiHelper.updatePrivateToken(token);
    }

    @Override
    public Observable<String> getLanguageFileFromServer(String url) {
        return mApiHelper.getLanguageFileFromServer(url);
    }

    @Override
    public Observable<APIResponse> login(String user, String pw, String tuneId) {
        return null;
    }
}

