package com.lyldemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.lyldemo.base.BuildConfig;
import com.lyldemo.common.ShareToPlatform;
import com.lyldemo.entity.RequestBannerInfo;
import com.lyldemo.interfaces.HasEditSharePopWindowCallBack;
import com.lyldemo.interfaces.PopupWindowCallBack;
import com.lyldemo.interfaces.SharePopupWindowCallBack;
import com.lyldemo.interfaces.ShareReporCallBack;
import com.lyldemo.listener.TencentCallbackListener;
import com.lyldemo.view.MyToast;
import com.lyldemo.view.SharePopupWindow;
import com.rxretrofitdemo.R;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

/**
 * Created by Jade.Liu on 2017/10/18.
 */

public class CommonShareUtils implements SharePopupWindowCallBack {

    private static int WECHAT_KEY = 1;
    private static int MOMENTS_KEY = 2;
    private Context mContext;
    private SharePopupWindow sharePopupWindow;
    private boolean hasEditBox;
    private RequestBannerInfo requestBannerInfo;
    private HasEditSharePopWindowCallBack editSharePopWindowCallBack;
    private ShareReporCallBack shareReporCallBack;
    private PopupWindowCallBack callBack;
    private String mUrl;
    private String mContent;
    private String mDescription;
    private Bitmap mThumb;
    private Bitmap mBitmap;
    private String mImageUrl;
    private boolean isShowReport;
    private IWXAPI mIWXAPI;

    private TencentCallbackListener listener;
    private static SharedCallBack sharedCallBack;

    @Override
    public void popupWindowSelect(ShareToPlatform index) {
        ArrayList<String> list = new ArrayList<String>();
        switch (index) {
            case SHARE_TO_QQ:
                list.add(mImageUrl);
                shareTencentQQ(mUrl, mContent, mDescription, list);
                break;
            case SHARE_TO_WECHAT:
                setShareWeiXin(WECHAT_KEY, mUrl, mContent, mDescription, mThumb, mBitmap);
                break;
            case SHARE_TO_MOMENTS:
                setShareWeiXin(MOMENTS_KEY, mUrl, mContent, mDescription, mThumb, mBitmap);
                break;
            case SHARE_TO_QZONE:
                list.add(mImageUrl);
                shareTencentQzone(mUrl, mContent, mDescription, list);
                break;
        }
    }

    public interface SharedCallBack {
        void successed();

        void failed();
    }

    /**
     * 默认不显示编辑栏
     *
     * @param mContext
     * @param requestBannerInfo 广告请求实体类
     */
    public CommonShareUtils(Context mContext, RequestBannerInfo requestBannerInfo, ShareReporCallBack shareReporCallBack) {
        this(mContext, false, requestBannerInfo, null, shareReporCallBack);
    }

    /**
     * 默认显示编辑栏
     *
     * @param mContext
     * @param requestBannerInfo          广告请求实体类
     * @param editSharePopWindowCallBack 编辑栏事件回调接口
     */
    public CommonShareUtils(Context mContext, RequestBannerInfo requestBannerInfo, HasEditSharePopWindowCallBack editSharePopWindowCallBack, ShareReporCallBack shareReporCallBack) {
        this(mContext, true, requestBannerInfo, editSharePopWindowCallBack, shareReporCallBack);
    }

    public CommonShareUtils(Context context, boolean hasEditBox, RequestBannerInfo requestBannerInfo, HasEditSharePopWindowCallBack editSharePopWindowCallBack, ShareReporCallBack shareReporCallBack) {
        this.hasEditBox = hasEditBox;
        this.requestBannerInfo = requestBannerInfo;
        this.editSharePopWindowCallBack = editSharePopWindowCallBack;
        this.shareReporCallBack = shareReporCallBack;
        this.mContext = context;
        if (mIWXAPI == null) {
            mIWXAPI = WXUtil.initWXAPI(context);
        }
        sharePopupWindow = new SharePopupWindow(context);
    }

    public void setDeleteText(String text) {
        sharePopupWindow.setDeleteText(text);
    }

    public void setDeleteIcon(int iconID) {
        sharePopupWindow.setDeleteIcon(iconID);
    }

    public void shareText(String content, String description) {
        share(null, content, description, null, null, null);
    }

    public void shareLink(String url, String content, String description, Bitmap thumb, String imgUrl) {
        share(url, content, description, thumb, null, imgUrl);
    }

    public void shareLink(String url, String content, String description, Bitmap thumb, String imgUrl, TencentCallbackListener listener, SharedCallBack sharedCallBack) {
        share(url, content, description, thumb, null, imgUrl);
        this.listener = listener;
        this.sharedCallBack = sharedCallBack;
    }

    public void shareImage(String content, String description, Bitmap thumb, Bitmap bitmap) {
        share(null, content, description, thumb, bitmap, null);
    }

    private void share(String url, String content, String description, Bitmap thumb, Bitmap bitmap, String imgUrl) {
        initData(url, content, description, thumb, bitmap, imgUrl);
        sharePopupWindow.showPopupWindow(hasEditBox, requestBannerInfo, this, editSharePopWindowCallBack, shareReporCallBack);
    }

    private void initData(String url, String content, String description, Bitmap thumb, Bitmap bitmap, String imgUrl) {
        mUrl = url;
        mContent = content;
        mDescription = description;
        mThumb = thumb;
        mBitmap = bitmap;
        if (StringUtil.isEmpty(imgUrl)) {
            mImageUrl = BuildConfig.RESTFUL_IMAGE_HOST + "Resources/Images/share/icon_share.png";
        } else {
            mImageUrl = imgUrl;
        }
    }

    private void setShareWeiXin(int type, String url, String content, String description, Bitmap thumb, Bitmap bitmap) {
        if (mIWXAPI != null) {
            if (mIWXAPI.getWXAppSupportAPI() == 0) {
                MyToast.show(mContext, "请安装微信APP");
                return;
            }
            if (type == WECHAT_KEY) {
                shareWeiXin(SendMessageToWX.Req.WXSceneSession, url, content, description, thumb, bitmap);
            } else if (type == MOMENTS_KEY) {
                if (mIWXAPI.getWXAppSupportAPI() >= 0x21020001) {
                    shareWeiXin(SendMessageToWX.Req.WXSceneTimeline, url, content, description, thumb, bitmap);
                } else {
                    MyToast.show(mContext, "微信4.2以上支持分享到朋友圈");
                }
            }
        }
    }

    private void shareWeiXin(int scene, String url, String content, String description, Bitmap thumb, Bitmap bitmap) {
        WXMediaMessage msgMediaMessage = null;
        if (description.length() > 500) {
            description = description.substring(0, 500);
        }
        if (url != null && !"".equals(url.trim())) {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;

            msgMediaMessage = new WXMediaMessage(webpage);
            msgMediaMessage.title = content;
            msgMediaMessage.description = description;
            if (thumb != null) {
                msgMediaMessage.setThumbImage(thumb);
                // msgMediaMessage.thumbData = BitmapUtil.bmpToByteArray(thumb,
                // true);
            }
        } else if (bitmap != null) {
            WXImageObject imageObject = new WXImageObject(bitmap);

            msgMediaMessage = new WXMediaMessage();
            msgMediaMessage.mediaObject = imageObject;
            msgMediaMessage.title = content;
            msgMediaMessage.description = description;
            if (thumb != null) {
                msgMediaMessage.setThumbImage(thumb);
                // msgMediaMessage.thumbData = BitmapUtil.bmpToByteArray(thumb,
                // true);
            }
        } else {
            WXTextObject textObj = new WXTextObject();
            textObj.text = content;

            msgMediaMessage = new WXMediaMessage();
            msgMediaMessage.mediaObject = textObj;
            msgMediaMessage.description = description;
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msgMediaMessage;
        req.scene = scene;

        mIWXAPI.sendReq(req);
    }

    /**
     * 暂不支持传多张图片
     *
     * @param url
     * @param content
     * @param description
     * @param imgUrlList
     */
    private void shareTencentQzone(String url, String content, String description, ArrayList<String> imgUrlList) {
        if (description.length() > 500) {
            description = description.substring(0, 500);
        }
        Bundle bundle = new Bundle();
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, content);// 必填
        if (StringUtil.isEmpty(description)) {
            bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, content);// 选填
        } else {
            bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, description);// 选填
        }
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);// 必填
        bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);
        if (listener != null) {
            listener.setSharedCallBack(sharedCallBack);
            TencentUtil.initTencentAPI(mContext).shareToQzone((Activity) mContext, bundle, listener);
        } else {
            TencentUtil.initTencentAPI(mContext).shareToQzone((Activity) mContext, bundle, new IUiListener() {
                @Override
                public void onComplete(Object arg0) {
                    Log.i("TAG", "onComplete: " + arg0.toString());
                }

                @Override
                public void onError(UiError uiError) {
                    Log.i("TAG", "onComplete: " + uiError.toString());
                }

                @Override
                public void onCancel() {
                }
            });
        }
    }

    /**
     * 分享到QQ
     *
     * @param url
     * @param content
     * @param description
     * @param imgUrlList
     */
    private void shareTencentQQ(String url, String content, String description, ArrayList<String> imgUrlList) {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, content);
        if (StringUtil.isEmpty(description)) {
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
        } else {
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
        }
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        if (imgUrlList != null && imgUrlList.size() > 0) {
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList.get(0));
        }
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, ((Activity) mContext).getResources().getString(R.string.app_name));
        // bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, "其他附加功能");
        if (listener != null) {
            listener.setSharedCallBack(sharedCallBack);
            TencentUtil.initTencentAPI(mContext).shareToQQ((Activity) mContext, bundle, listener);
        } else {
            TencentUtil.initTencentAPI(mContext).shareToQQ((Activity) mContext, bundle, new IUiListener() {
                @Override
                public void onComplete(Object arg0) {
                }

                @Override
                public void onError(UiError uiError) {
                }

                @Override
                public void onCancel() {
                }
            });
        }
    }

    public static void setWXSharedCallBack(boolean isSuccess) {
        if (sharedCallBack == null) {
            return;
        }
        if (isSuccess) {
            sharedCallBack.successed();
        } else {
            sharedCallBack.failed();
        }
    }
}
