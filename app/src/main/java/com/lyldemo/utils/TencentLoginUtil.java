package com.lyldemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lyldemo.entity.ThirdLoginEntity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class TencentLoginUtil {

    private Tencent mTencent;
    private Context mContext;

    public TencentLoginUtil(Context context) {
        mContext = context;
        if (mTencent == null) {
            mTencent = TencentUtil.initTencentAPI(mContext);
        }
    }

    public void login() {
        if (mTencent != null) {
            if (!mTencent.isSessionValid()) {
                //get_user_info get_simple_userinfo
                mTencent.login((Activity) mContext, "get_user_info", getIUiListener(mContext));
            }
        }
    }

    public static void backResult(Context context, int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, getIUiListener(context));
        }
    }

    public static IUiListener getIUiListener(final Context context) {
        return new IUiListener() {

            @Override
            public void onCancel() {
            }

            @Override
            public void onComplete(Object arg0) {
                if (arg0 != null) {
                    JSONObject jsonObject = (JSONObject) arg0;
                    String openid = "";
                    String accessToken = "";
                    try {
                        openid = jsonObject.getString("openid");
                        accessToken = jsonObject.getString("access_token");
                    } catch (JSONException e) {
                    }
                    ThirdLoginEntity thirdLoginEntity=new ThirdLoginEntity();
                    thirdLoginEntity.setAccessToken(accessToken);
                    thirdLoginEntity.setCode(openid);
                    JointLoginUtil.sendMessage(JointLoginUtil.SUCCESS_REUSLT_QQ_KEY, thirdLoginEntity);
                }
            }

            @Override
            public void onError(UiError e) {
            }
        };
    }
}
