package com.luan.fchat.model.user;

import java.io.Serializable;

public class ParentProfile implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
