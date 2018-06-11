package com.lyldemo.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lyldemo.entity.ThirdLoginEntity;
import com.lyldemo.entity.UserAllInfo;
import com.lyldemo.listener.CheckLoginListener;

public class JointLoginUtil {
    //QQ登录
    public static final int JOINT_LOGIN_QQ_KEY = 100;
    //支付宝登录
    public static final int JOINT_LOGIN_ALIPAY_KEY = 101;
    //新浪微博
    public static final int JOINT_LOGIN_SINA_KEY = 102;
    //微信
    public static final int JOINT_LOGIN_WEIXIN_KEY = 103;

    public static final int SUCCESS_REUSLT_QQ_KEY = 200;
    public static final int SUCCESS_REUSLT_ALIPAY_KEY = 201;
    public static final int SUCCESS_REUSLT_SINA_KEY = 202;
    public static final int SUCCESS_REUSLT_WEIXIN_KEY = 203;
    private static final int FAIL_REUSLT_KEY = 300;
    private static int bindingTypes;

    private static Handler mHandler;

    private static CheckLoginListener mCheckLoginListener;
    private static Bundle mCheckLoginParamsBundle;

    public static void login(Context context, CheckLoginListener checkLoginListener, Bundle checkLoginParamsBundle, int type, int bindingType) {
        mCheckLoginListener = checkLoginListener;
        mCheckLoginParamsBundle = checkLoginParamsBundle;
        bindingTypes = bindingType;
        setHandler(context);
        switch (type) {
            case JOINT_LOGIN_QQ_KEY:
                new TencentLoginUtil(context).login();
                break;
            case JOINT_LOGIN_WEIXIN_KEY:
                new WXLoginUtil(context).login();
                break;
            default:
                break;
        }
    }


    public static void backResult(Context context, int requestCode, int resultCode, Intent data) {
        TencentLoginUtil.backResult(context, requestCode, resultCode, data);
    }

    public static void sendMessage(int what, Object object) {
        if (mHandler != null) {
            Message message = new Message();
            message.what = what;
            message.obj = object;
            mHandler.sendMessage(message);
        }
    }

    /**
     * 发送失败消息
     *
     * @param error
     */
    public static void sendFailMessage(String error) {
        if (mHandler != null) {
            Message message = new Message();
            message.what = FAIL_REUSLT_KEY;
            message.obj = error;

            mHandler.sendMessage(message);
        }
    }

    private static void setHandler(final Context context) {
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    ThirdLoginEntity result = null;
                    if (msg.obj != null) {
                        try {
                            result = (ThirdLoginEntity) msg.obj;
                        } catch (Exception e) {
                        }
                    }
                    switch (msg.what) {
                        case SUCCESS_REUSLT_QQ_KEY:
                            if (bindingTypes == 0) {
                                roadService(context, 5, result);
                            } else {
                                roadServiceBind(context, 5, result);
                                bindingTypes = 0;
                            }
                            break;
                        case SUCCESS_REUSLT_ALIPAY_KEY:
                            roadService(context, 0, result);
                            break;
                        case SUCCESS_REUSLT_SINA_KEY:
                            roadService(context, 10, result);
                            break;
                        case SUCCESS_REUSLT_WEIXIN_KEY:
                            if (bindingTypes == 0) {
                                roadService(context, 15, result);
                            } else {
                                roadServiceBind(context, 15, result);
                                bindingTypes = 0;
                            }


                            break;
                        default:
                            break;
                    }
                }
            };
        }
    }

    private static int types;

    private static void roadService(final Context context, final int id, final ThirdLoginEntity result) {
//        types = id;
//        if (id > 0) {
//            HttpRequesParams params = new HttpRequesParams(Constant.HOME_LOGIN_URL);
//            params.addBodyParameter("id", id + "");
//            params.addBodyParameter("code", result.getCode());
//            params.addBodyParameter("accessToken", result.getAccessToken());
//            HttpUtils.get(context, true, params, new HttpResponseCallBack() {
//                @Override
//                public void onSuccess(String result) {
//                    final Gson gson = new Gson();
//                    Type type = new TypeToken<ResultData<UserAllInfo>>() {
//                    }.getType();
//                    ResultData<UserAllInfo> resultData = gson.fromJson(result, type);
//                    if (resultData.isSuccess()) {
//                        if (resultData.getData() != null) {
//                            success(context, resultData.getData());
//
//                        } else {
//                        }
//                    } else {
//                        if (!StringUtil.isEmpty(resultData.getMessage())) {
//                            MyToast.show(context, resultData.getMessage());
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailed(String failedMsg) {
//                    MyToast.show(context, failedMsg);
//                }
//
//            });
//        }
    }


    /**
     * 去綁定
     */
    private static void roadServiceBind(final Context context, final int id, final ThirdLoginEntity result) {
//        JSONObject object = new JSONObject();
//        try {
//            object.put("partner", id);
//            object.put("openId", result.getCode());
//            object.put("sysNo", UserInfoUtils.getUserInfo().getSysNo());
////                object.put("userId", UserInfoUtils.getUserInfo().getSysNo());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        HttpRequesParams params = new HttpRequesParams(Constant.BIND_EXISTS_ACCOUNT_URL);
//        params.setBodyContent(object.toString());
//        HttpUtils.post(context, true, params, new HttpResponseCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                final Gson gson = new Gson();
//                Type type = new TypeToken<ResultData<UserAllInfo>>() {
//                }.getType();
//                ResultData<UserAllInfo> resultData = gson.fromJson(result, type);
//                if (resultData.isSuccess()) {
//                    HttpUtils.cacheCookie();
//                    UserAllInfo userAllInfo = UserInfoUtils.getUserInfo();
//                    if (id == 5) {
//                        userAllInfo.setHasBindQQ(true);
//                    } else if (id == 15) {
//                        userAllInfo.setHasBindWeChat(true);
//                    }
//                    UserInfoUtils.cacheLogin(true);
//                    UserInfoUtils.cacheUserInfo(userAllInfo);
//                    MyToast.show(context, resultData.getMessage());
//                } else {
//                    MyToast.show(context, resultData.getMessage());
//                }
//                Activity activity = (Activity) context;
//                activity.finish();
//                activity = null;
//            }
//
//            @Override
//            public void onFailed(String failedMsg) {
//                MyToast.show(context, failedMsg);
//            }
//
//        });
    }


    /**
     * 登陆成功
     *
     * @param context
     * @param customerInfo
     */
    private static void success(Context context, UserAllInfo customerInfo) {
//        MySharedCache.put("threed_login",true);
//        HttpUtils.cacheCookie();
//        UserInfoUtils.cacheUserInfo(customerInfo);
//        int thirdPartyUserSysNo = customerInfo.getThirdPartyUserSysNo();
//        if (!customerInfo.isHasBindPhone()) {
//            Bundle bundle = new Bundle();
//            bundle.putInt("types", types);
//            IntentUtil.redirectToNextActivity(context, AccountBindActivity.class, bundle);
//        } else if (mCheckLoginListener != null) {
//            UserInfoUtils.cacheLogin(true);
//            mCheckLoginListener.OnLogined(customerInfo, mCheckLoginParamsBundle);
//        }else{
//            UserInfoUtils.cacheLogin(true);
//        }
//        Activity activity = (Activity) context;
//        activity.finish();
//        activity = null;
//        mCheckLoginListener = null;
//        mCheckLoginParamsBundle = null;
//        mHandler = null;

    }


}
