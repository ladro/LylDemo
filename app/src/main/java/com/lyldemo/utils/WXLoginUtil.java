package com.lyldemo.utils;

import android.content.Context;

import com.lyldemo.view.MyToast;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;


public class WXLoginUtil {

    private IWXAPI mIWXAPI;
    private Context mContext;

    private String mNonceStr;

    public WXLoginUtil(Context context) {
        if (mIWXAPI == null) {
            mNonceStr = null;
            mContext = context;
            mIWXAPI = WXUtil.initWXAPI(context);
        }
    }

    public void login() {
        if (mIWXAPI != null) {
            if (mIWXAPI.getWXAppSupportAPI() == 0) {
                MyToast.show(mContext, "请安装微信APP");
                return;
            }
        }
        mNonceStr = WXUtil.getNonceStr();
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";//应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
        req.state = mNonceStr;//用于保持请求和回调的状态，授权请求后原样带回给第三方
        mIWXAPI.sendReq(req);
    }

}
