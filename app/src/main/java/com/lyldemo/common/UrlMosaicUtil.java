package com.lyldemo.common;


import java.io.File;

/**
 * url 拼接工具类
 * Created by Jade.Liu on 2017/10/17.
 */

public class UrlMosaicUtil {
    /**
     * 分页url拼接
     *
     * @param url
     * @param pageIndex
     * @return
     */
    public static String getPageUrl(String url, int pageIndex) {
        return url + File.separator + pageIndex + File.separator + Constant.PAGE_SIZE;
    }

    public static String getPageUrl2(String url, int pageIndex, int coursie) {
        return url + File.separator+coursie+ File.separator + pageIndex + File.separator + Constant.PAGE_SIZE;
    }



    public static String getPageUrl2(String url, int pageIndex) {
        return url + File.separator + pageIndex + File.separator + Constant.PAGE_SIZE_SEL;
    }

    /**
     * url 参数拼接
     *
     * @param url
     * @param parameters
     * @return
     */
    public static String getUrl(String url, String... parameters) {
        StringBuffer sb = new StringBuffer();
        for (String str : parameters) {
            sb.append(str).append(File.separator);
        }
        String parStr = sb.toString();
        return url + File.separator + parStr.substring(0, parStr.length());
    }

    /**
     * 上传图片URL拼接
     *
     * @param par
     * @return
     */
    public static String getUploadImgUrl(String par) {
        return Constant.UPLOAD_FILE + par;
    }
}
