package com.lyldemo.listener;


import com.lyldemo.utils.CommonShareUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class TencentCallbackListener implements IUiListener {

    private CommonShareUtils.SharedCallBack sharedCallBack;

    public void setSharedCallBack(CommonShareUtils.SharedCallBack sharedCallBack) {
        this.sharedCallBack = sharedCallBack;
    }

    @Override
    public void onCancel() {
        if (sharedCallBack != null) {
            sharedCallBack.failed();
        }
    }

    @Override
    public void onComplete(Object arg0) {
        if (arg0.toString().contains("\"ret\":0") && sharedCallBack != null) {
            sharedCallBack.successed();
        }
    }

    @Override
    public void onError(UiError arg0) {
        if (sharedCallBack != null) {
            sharedCallBack.failed();
        }
    }

}
