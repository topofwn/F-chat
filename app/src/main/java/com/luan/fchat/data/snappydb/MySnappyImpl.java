package com.luan.fchat.data.snappydb;

import android.content.Context;
import android.support.annotation.NonNull;

import com.luan.fchat.model.user.ParentProfile;
import com.luan.fchat.utils.OGILVYLog;
import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import java.io.Serializable;


/**
 * Created by giaotuan on 12/10/16.
 */

public class MySnappyImpl {

    private final String dbEMAN = "olimbd";
    private DB snappydb;
    private static MySnappyImpl mInstance;

    public static MySnappyImpl newInstance(@NonNull Context context) {
        if (mInstance == null) {
            mInstance = new MySnappyImpl(context);
        }
        return mInstance;
    }

    private MySnappyImpl(Context application) {
        if (snappydb == null) {
            try {
                snappydb = new SnappyDB.Builder(application).name(dbEMAN).build();
            } catch (SnappydbException e) {
                OGILVYLog.l(e);
            }
        }
    }

    public <T extends Serializable> T getObject(String key, Class<T> className) {
        try {
            T obj = snappydb.get(key, className);
            return obj;
        } catch (SnappydbException e) {
            OGILVYLog.logTuan("getObject", e.getMessage() + key, MySnappyImpl.class);
        }
        return null;
    }

    public void saveObject(Object obj, String key) {
        try {
            snappydb.put(key, obj);
        } catch (SnappydbException e) {
            OGILVYLog.logTuan("saveObject", e.getMessage() + key, MySnappyImpl.class);
        }
    }

    public void removeObjectByKey(String key) {
        try {
            snappydb.del(key);
        } catch (SnappydbException e) {
            OGILVYLog.logTuan("removeObjectByKey", e.getMessage() + key, MySnappyImpl.class);
        }
    }

    public String getMainLanguage() {
        try {
            return snappydb.get(SnapKey.SNAPPY_KEY_LANG);
        } catch (SnappydbException e) {
            OGILVYLog.logTuan("getObject", e.getMessage() + SnapKey.SNAPPY_KEY_LANG, MySnappyImpl.class);
        }
        return null;
    }

    public void setMainLanguage(String data) {
        try {
            snappydb.put(SnapKey.SNAPPY_KEY_LANG, data);
        } catch (SnappydbException e) {
            OGILVYLog.logTuan("saveObject", e.getMessage() + SnapKey.SNAPPY_KEY_LANG, MySnappyImpl.class);
        }
    }

    public ParentProfile getCurrentUser() {
        return getObject(SnapKey.SNAPPY_KEY_CURRENT_USER, ParentProfile.class);
    }

    public void setCurrentUser(ParentProfile user) {
        saveObject(user, SnapKey.SNAPPY_KEY_CURRENT_USER);
    }
}

