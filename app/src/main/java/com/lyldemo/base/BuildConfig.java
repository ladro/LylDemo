package com.lyldemo.base;

/**
 * Created by Jade.Liu on 2017/8/13.
 */

public class BuildConfig {
    //是否打印日志
    public static final boolean DEBUG = true;
    //http 访问地址
    //测试环境 QA
   public static final String MSITE_URL = "http://192.168.60.60/#/";
    public static final String HREF_URL = "http://bauhiniavalley/";
    public static final String RESTFUL_SERVICE_HOST = "http://192.168.60.60/v1/api/";
    public static final String RESTFUL_IMAGE_HOST = "http://imageservice.bauhiniavalley.com.qa/";
    //测试环境 pre
  /*  public static final String MSITE_URL = "http://192.168.60.60/#/";
    public static final String HREF_URL = "http://bauhiniavalley/";
    public static final String RESTFUL_SERVICE_HOST = "http://10.10.9.207:80/v1/api/";
    public static final String RESTFUL_IMAGE_HOST = "http://img.bauhiniavalley.com.pre/";*/

    //正式环境
//    public static final String MSITE_URL = "http://gw.bauhiniavalley.com/#/";
//    public static final String HREF_URL = "http://bauhiniavalley/";
//    public static final String RESTFUL_SERVICE_HOST = "http://gw.bauhiniavalley.com/v1/api/";
//    public static final String RESTFUL_IMAGE_HOST = "http://img.bauhiniavalley.com/";

}
