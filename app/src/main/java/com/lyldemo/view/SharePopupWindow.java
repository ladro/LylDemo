package com.lyldemo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lyldemo.activity.LoginActivity;
import com.lyldemo.common.Constant;
import com.lyldemo.common.ShareToPlatform;
import com.lyldemo.common.UrlMosaicUtil;
import com.lyldemo.entity.BannerInfo;
import com.lyldemo.entity.RequestBannerInfo;
import com.lyldemo.entity.UserAllInfo;
import com.lyldemo.interfaces.HasEditSharePopWindowCallBack;
import com.lyldemo.interfaces.SharePopupWindowCallBack;
import com.lyldemo.interfaces.ShareReporCallBack;
import com.lyldemo.listener.CheckLoginListener;
import com.lyldemo.utils.ImageLoaderUtil;
import com.lyldemo.utils.StringUtil;
import com.lyldemo.utils.UrlConversionUtils;
import com.lyldemo.utils.UrlLoadUtil;
import com.lyldemo.utils.UserInfoUtils;
import com.lyldemo.xutilshttp.HttpRequesParams;
import com.lyldemo.xutilshttp.HttpResponseCallBack;
import com.lyldemo.xutilshttp.HttpUtils;
import com.lyldemo.xutilshttp.entity.ResultData;
import com.rxretrofitdemo.R;

import java.lang.reflect.Type;

/**
 * Created by liu.yao on 2017/4/24.
 */

public class SharePopupWindow {


    private PopupWindow mPopupWindow;
    private Context context;
    private SharePopupWindowCallBack callBack;
    private HasEditSharePopWindowCallBack editSharePopWindowCallBack;
    private ShareReporCallBack shareReporCallBack;
    private boolean hasEditBox;
    private RequestBannerInfo requestBannerInfo;
    private ImageView advertImg;
    private View mView;
    private LinearLayout retportLayout;
    private String deleteText;
    private int iconID;

    public SharePopupWindow(Context context) {
        this.context = context;
    }

    public void setDeleteText(String text) {
        this.deleteText = text;
    }

    private void initPopupWindowView() {
        mView = LayoutInflater.from(context).inflate(R.layout.select_share_popupwindow_layout, null);
        LinearLayout editContainerLayout = mView.findViewById(R.id.share_pop_edit_container_layout);
        retportLayout = mView.findViewById(R.id.share_pop_report);
        advertImg = mView.findViewById(R.id.share_pop_advert_img);
        advertImg.setVisibility(View.GONE);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mView.findViewById(R.id.share_pop_close_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });
        mView.findViewById(R.id.share_pop_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.popupWindowSelect(ShareToPlatform.SHARE_TO_QQ);
                closePopupWindow();
            }
        });
        mView.findViewById(R.id.share_pop_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.popupWindowSelect(ShareToPlatform.SHARE_TO_WECHAT);
                closePopupWindow();
            }
        });
        mView.findViewById(R.id.share_pop_moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.popupWindowSelect(ShareToPlatform.SHARE_TO_MOMENTS);
                closePopupWindow();
            }
        });
        mView.findViewById(R.id.share_pop_qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.popupWindowSelect(ShareToPlatform.SHARE_TO_QZONE);
                closePopupWindow();
            }
        });
        mView.findViewById(R.id.share_pop_qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.popupWindowSelect(ShareToPlatform.SHARE_TO_QZONE);
                closePopupWindow();
            }
        });
        mView.findViewById(R.id.share_pop_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shareReporCallBack != null) {
                    shareReporCallBack.reportBtnOnClickListener();
                    closePopupWindow();
                }
            }
        });
        mView.findViewById(R.id.share_pop_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoUtils.checkLogin(context, LoginActivity.class, new CheckLoginListener() {
                    @Override
                    public void OnLogined(UserAllInfo customer, Bundle bundle) {
                        editSharePopWindowCallBack.editBtnOnClickListener();
                        closePopupWindow();
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {

                    }
                }, new Bundle());
            }
        });
        TextView deleteTextBtn = mView.findViewById(R.id.share_pop_delete_btn);
        if (!StringUtil.isEmpty(deleteText)) {
            deleteTextBtn.setText(deleteText);
        }
        if (iconID > 0) {
            Drawable drawable = context.getResources().getDrawable(iconID);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            deleteTextBtn.setCompoundDrawables(null, drawable, null, null);
        }
        deleteTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoUtils.checkLogin(context, LoginActivity.class, new CheckLoginListener() {
                    @Override
                    public void OnLogined(UserAllInfo customer, Bundle bundle) {
                        editSharePopWindowCallBack.deleteBtnOnClickListener();
                        closePopupWindow();
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {

                    }
                }, new Bundle());

            }
        });
        editContainerLayout.setVisibility(hasEditBox ? View.VISIBLE : View.GONE);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

    }

    public void closePopupWindow() {
        mPopupWindow.dismiss();
    }


    public void showPopupWindow(boolean hasEditBox, RequestBannerInfo requestBannerInfo, SharePopupWindowCallBack callBack, HasEditSharePopWindowCallBack editSharePopWindowCallBack, ShareReporCallBack shareReporCallBack) {
        this.hasEditBox = hasEditBox;
        this.callBack = callBack;
        this.requestBannerInfo = requestBannerInfo;
        this.editSharePopWindowCallBack = editSharePopWindowCallBack;
        this.shareReporCallBack = shareReporCallBack;
        getBannerData();
        if (mPopupWindow != null) {
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                try {
                    initPopupWindowView();
                    mPopupWindow.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                } catch (Exception e) {
                }
            }
        } else {
            initPopupWindowView();
            mPopupWindow.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        }
        if (shareReporCallBack == null) {
            retportLayout.setVisibility(View.GONE);
        } else {
            retportLayout.setVisibility(View.VISIBLE);
        }

    }

    private void getBannerData() {
        HttpRequesParams params = new HttpRequesParams(UrlMosaicUtil.getUrl(Constant.BANNER_IMG_URL, requestBannerInfo.getPageId().getValue() + "", requestBannerInfo.getPostion().getValue() + ""));
        HttpUtils.get(context, false, params, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<ResultData<BannerInfo>>() {
                }.getType();
                final ResultData<BannerInfo> resultData = gson.fromJson(result, type);
                if (resultData.isSuccess() && resultData.getData() != null) {
                    String imgUrl = UrlConversionUtils.getUploadImgUrl(resultData.getData().getImageSrc().get(0));
                    if (!TextUtils.isEmpty(imgUrl)) {
                        ImageLoaderUtil.displayImage(imgUrl, advertImg, R.mipmap.default_pics);
                        advertImg.setVisibility(View.VISIBLE);
                    } else {
                        advertImg.setVisibility(View.GONE);
                    }
                    advertImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(resultData.getData().getHref())) {
                                UrlLoadUtil.jumpeLink(context, resultData.getData().getHref());
                                closePopupWindow();
                            }
                        }
                    });
                } else {
                    advertImg.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailed(String failedMsg) {

            }
        });

    }

    public void setDeleteIcon(@DrawableRes int iconID) {
        this.iconID = iconID;
    }
}
