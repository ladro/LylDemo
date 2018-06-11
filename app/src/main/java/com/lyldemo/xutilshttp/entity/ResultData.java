package com.lyldemo.xutilshttp.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jade.Liu on 2017/8/14.
 */

public class ResultData<T> implements Serializable {


    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
