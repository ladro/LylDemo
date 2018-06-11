package com.lyldemo.xutilshttp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.lyldemo.base.BaseApp;
import com.lyldemo.common.Constant;
import com.lyldemo.utils.UserInfoUtils;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.HttpException;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jade.Liu on 2017/8/13.
 */

public class HttpUtils {

    private static final String REQUEST_COOKIE_KEY = "Cookie";

    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;


    public static void loginReg(Context context, boolean isShown, HttpRequesParams params, final HttpResponseCallBack callBack) {
        setDefaultHeader(params);
//        final Dialog mDialog = DialogUtils.createLoadingDialog(context, isShown);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    httpExceptionHandler(ex, callBack);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
//                DialogUtils.closeDialog(mDialog);
            }
        });
    }


    public static void get(Context context, boolean isShowLoading, HttpRequesParams params, final HttpResponseCallBack callBack) {
        setDefaultHeader(params);
//        final Dialog mDialog = DialogUtils.createLoadingDialog(context, isShowLoading);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    httpExceptionHandler(ex, callBack);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                try {
//                    DialogUtils.closeDialog(mDialog);
                } catch (Exception e) {
                }
            }
        });
    }

    public static void post(Context context, boolean isShowLoading, HttpRequesParams params, final HttpResponseCallBack callBack) {
        setDefaultHeader(params);
//        final Dialog mDialog = DialogUtils.createLoadingDialog(context, isShowLoading);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    httpExceptionHandler(ex, callBack);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                try {
//                    DialogUtils.closeDialog(mDialog);
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     * @param savePath
     * @param callBack
     */
    public static void downloadFile(Context context, String url, String savePath, HttpResponseCallBack callBack) {
        String urlTf8 = "";
        try {
            urlTf8 = URLEncoder.encode(url, "UTF-8").replaceAll("\\+", "%20");
            urlTf8 = urlTf8.replaceAll("%3A", ":").replaceAll("%2F", "/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final HttpRequesParams requestParams = new HttpRequesParams(urlTf8);
        requestParams.setSaveFilePath(BASE_PATH + savePath);
        requestParams.setAutoRename(true);//断点下载
        requestParams.setExecutor(new PriorityExecutor(2, true));
        get(context, false, requestParams, callBack);
    }

    /**
     * 上传文件
     *
     * @param context
     * @param url
     * @param uploadeFilePath
     * @param callBack
     */
    public static void uploadFile(Context context, String url, String uploadeFilePath, HttpResponseCallBack callBack) {
        HttpRequesParams params = new HttpRequesParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(uploadeFilePath));
        post(context, false, params, callBack);
    }

    public static void cacheCookie() {
        DbCookieStore instance = DbCookieStore.INSTANCE;
        List<HttpCookie> cookies = instance.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
            HttpCookie cookie = cookies.get(i);
            if (cookie.getName() != null && cookie.getName().equals(Constant.AUTH_COOKIE_NAME)) {
                UserInfoUtils.cacheAuth(Constant.AUTH_COOKIE_NAME + "=" + cookie.getValue());
            }
        }
    }

    /**
     * 设置默认请求header
     *
     * @param params
     */
    private static void setDefaultHeader(HttpRequesParams params) {
        params.addHeader("User-Agent", "BauhiniaValley Android/" + getCurrentVersion());
        params.addHeader("Accept-Language", getLocaleLanguage());
        DbCookieStore instance = DbCookieStore.INSTANCE;
        List<HttpCookie> cookies = instance.getCookies();
        if (cookies == null || cookies.size() >= 0 && UserInfoUtils.getAuth() != null) {
            instance.removeAll();
            params.addHeader(REQUEST_COOKIE_KEY, UserInfoUtils.getAuth());
        }
    }

    private static String getLocaleLanguage() {
        Locale locale = BaseApp.instance().getResources().getConfiguration().locale;
        String language = locale.getCountry();
        if ("TW".equals(language) || "tw".equals(language) || "HK".equals(language) || "hk".equals(language)) {
            return "zh-HK";
        } else if ("CN".equals(language) || "cn".equals(language)) {
            return "zh-CN";
        }
        return "zh-CN";
    }

    private static void httpExceptionHandler(Throwable ex, HttpResponseCallBack callBack) {
        HttpException httpEx = (HttpException) ex;
        int responseCode = httpEx.getCode();
        String responseMsg = httpEx.getMessage();
        String errorResult = httpEx.getResult();
        if (responseCode >= 400 && responseCode < 500) {
            callBack.onFailed("抱歉，客户端错误，请稍后重试！");
        } else {
            callBack.onFailed("抱歉，服务端错误，请稍后重试！");
        }
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    private static String getCurrentVersion() {
        String clientVersion = null;
        try {
            clientVersion = BaseApp.instance().getPackageManager().getPackageInfo(BaseApp.instance().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return clientVersion;
    }

}
