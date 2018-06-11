package com.lyldemo.utils;

import android.content.Context;

import com.lyldemo.base.BuildConfig;

public class UrlLoadUtil {


    public static boolean promotionLink(final Context context, String url) {
        boolean isRedirect = false;

        return isRedirect;
    }


//http://bauhiniavalley/courseId/23
    public static boolean jumpeLink(final Context context, String url) {
        boolean isRedirect = false;
        if (!StringUtil.isEmpty(url)) {
            String link = url.replace(BuildConfig.HREF_URL, "");
            if (!StringUtil.isEmpty(link)) {
                link = link.toLowerCase().trim();
                if (link.contains("topic")) {
//                    isRedirect = redirectToTopicDesc(context, link);
                } else if (link.contains("news")) {
//                    isRedirect = redirectToNewsDesc(context, link);
                } else if (link.contains("active")) {
//                    isRedirect = redirectToActiveDesc(context, link);
                } else if(link.contains("courseid")){
//                    isRedirect = redirectToActiveID(context, link);
                }else{
//                    Bundle bundle = new Bundle();
//                    bundle.putString(OutWebViewActivity.OUT_WEB_VIEW_URL_KEY, url);
//                    IntentUtil.redirectToNextActivity(context, OutWebViewActivity.class, bundle);
                }
            }
        }
        return isRedirect;
    }

//    private static boolean redirectToActiveDesc(Context context, String link) {
//        boolean isRedirect = false;
//        int activeId = convertToInt(link.replace("active/", ""));
//        if (activeId > 0) {
//            isRedirect = true;
//            Bundle bundle = new Bundle();
//            bundle.putString(ActiveDetailActivity.SYSNO, activeId + "");
//            IntentUtil.redirectToNextActivity(context, ActiveDetailActivity.class, bundle);
//        }
//        return isRedirect;
//    }
//
//
//    private static boolean redirectToActiveID(Context context, String link) {
//        boolean isRedirect = false;
//        int activeId = convertToInt(link.replace("courseid/", ""));
//        if (activeId > 0) {
//            isRedirect=  getTopicData(context,activeId);
//        }
//
//        return isRedirect;
//    }
//    private static boolean getTopicData(final Context context, int sysNo ) {
//        final boolean[] isRedirects = {false};
//        HttpRequesParams params = new HttpRequesParams(Constant.SIGNCOURSE_SEARCHCOURSETOPIC_URL + File.separator + sysNo);
//        HttpUtils.get(context, false, params, new HttpResponseCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                Gson gson = new Gson();
//                Type type = new TypeToken<ResultData<String>>() {
//                }.getType();
//                ResultData<String> resultData = gson.fromJson(result, type);
//                if (resultData.isSuccess()) {
////                    strs = resultData.getData();
//                    isRedirects[0] =true;
//                    Bundle bundle = new Bundle();
//                    bundle.putBoolean("bool_get", true);
//                    IntentUtil.redirectToNextActivity(context, ShaoActivity.class,bundle);
//
//                } else {
//                    isRedirects[0] =false;
//                    Bundle bundle = new Bundle();
//                    bundle.putBoolean("bool_get", false);
//                    IntentUtil.redirectToNextActivity(context, ShaoActivity.class,bundle);
//
//                }
//            }
//
//            @Override
//            public void onFailed(String failedMsg) {
//                MyToast.show(context, failedMsg);
//            }
//        });
//
//        return isRedirects[0];
//    }



//    private static boolean redirectToNewsDesc(Context context, String link) {
//        boolean isRedirect = false;
//        int newsId = convertToInt(link.replace("news/", ""));
//        if (newsId > 0) {
//            isRedirect = true;
//            Bundle bundle = new Bundle();
//            bundle.putInt(NewsDetailsActivity.NEW_ID, newsId);
//            IntentUtil.redirectToNextActivity(context, NewsDetailsActivity.class, bundle);
//        }
//
//        return isRedirect;
//    }
//
//    private static boolean redirectToTopicDesc(Context context, String link) {
//        boolean isRedirect = false;
//        int topicId = convertToInt(link.replace("topic/", ""));
//        if (topicId > 0) {
//            isRedirect = true;
//            Bundle bundle = new Bundle();
//            bundle.putString(TopicDetailActivity.TOPIC_ID, topicId + "");
//            IntentUtil.redirectToNextActivity(context, TopicDetailActivity.class, bundle);
//        }
//
//        return isRedirect;
//    }

    private static int convertToInt(String value) {
        if (!StringUtil.isEmpty(value) && StringUtil.isNumber(value.trim())) {
            return Integer.parseInt(value.trim());
        }
        return 0;
    }
}
