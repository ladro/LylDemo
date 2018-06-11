package com.lyldemo.xutilshttp;

/**
 * Created by Jade.Liu on 2017/8/13.
 */

public interface HttpResponseCallBack {
    void onSuccess(String result);

    void onFailed(String failedMsg);
}
