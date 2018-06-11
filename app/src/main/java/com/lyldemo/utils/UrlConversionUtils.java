package com.lyldemo.utils;

import android.text.TextUtils;

import com.lyldemo.base.BuildConfig;


/**
 * Created by Jade.Liu on 2017/10/19.
 */
public class UrlConversionUtils {
    /**
     * 将imgurl相对地址转换成绝对地址
     *
     * @param url
     * @return
     */
    public static String getUploadImgUrl(String url) {
        String url1 = "";
        if (TextUtils.isEmpty(url)) {
            return url1;
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            url1 = url;
        } else {
            url1 = BuildConfig.RESTFUL_IMAGE_HOST + url;
        }
        return url1;
    }

    /**
     * 将imgurl相对地址转换成绝对地址
     *
     * @param url
     * @return
     */
    public static String getRelativeImgUrl(String url) {
        String url1 = "";
        if (TextUtils.isEmpty(url)) {
            return url1;
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            url1 = url.split(BuildConfig.RESTFUL_IMAGE_HOST)[1];
        } else {
            url1 = url;
        }
        return url1;
    }
}
