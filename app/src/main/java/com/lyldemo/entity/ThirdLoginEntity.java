package com.lyldemo.entity;

import java.io.Serializable;

/**
 * Created by Jade.Liu on 2018/1/25.
 */

public class ThirdLoginEntity implements Serializable {
    private String code;
    private String accessToken;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
