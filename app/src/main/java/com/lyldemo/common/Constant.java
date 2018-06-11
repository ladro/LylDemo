package com.lyldemo.common;


import com.lyldemo.base.BuildConfig;

/**
 * Created by Jade.Liu on 2017/10/12.
 */

public class Constant {

    //分页：一页数据条数
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_SIZE_SEL = 20;
    public static final String TOPIC = "topic";//话题上传图片字段
    public static final String ACTIVITY = "activity";//活动上传文件字段
    public static final String IDENTITY = "identity";//身份证上传图片字段
    public static final String AVATAR = "avatar";//头像上传图片字段
    public static final String UEDITOR = "ueditor";//富文本上传图片字段


    //缓存名称
    public static final String AUTH_COOKIE_NAME = "BauhiniaValleyAuth";
    public static final String APPLICATION_VERSION = "APPLICATION_VERSION";
    public static final String REMEMBER_LOGIN = "REMEMBER_LOGIN";
    public static final String USER_INFO = "USER_INFO";
    public static final String TOPIC_TITLE = "TOPIC_TITLE";
    public static final String TOPIC_DES = "TOPIC_DES";

    //banner图片地址
    public static final String BANNER_IMG_URL = BuildConfig.RESTFUL_SERVICE_HOST + "banner/loadbanner";
    //FIXME:FILE-MODULE(文件上传)
    public static final String UPLOAD_FILE = BuildConfig.RESTFUL_IMAGE_HOST + "UploadHandler.ashx?appName=";
}
