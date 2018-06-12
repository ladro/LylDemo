package com.lyldemo.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyldemo.common.IconLocationType;
import com.lyldemo.listener.TencentCallbackListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rxretrofitdemo.R;
import com.tencent.tauth.Tencent;

import org.xutils.x;

/**
 * Created by Jade.Liu on 2017/10/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected TencentCallbackListener listener;
    //    private Tracker tracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tracker = ((PiwikApplication) getApplication()).getTracker();
        x.view().inject(this);
        initView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listener = new TencentCallbackListener();
        getData();

        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#53e3a9"));
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //    public Tracker getTracker() {
//        return tracker;
//    }
//
//    public void setTracker(Tracker tracker) {
//        this.tracker = tracker;
//    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 没有title
     */
    public void untitled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar.isShowing()) {
            actionBar.hide();
        }
    }

    public void initTitleBar(String titleContent) {
        initTitleBar(true, titleContent);
    }

    public void initTitleBar(String titleContent, View.OnClickListener backOnClickListener) {
        initTitleBar(true, titleContent, backOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent) {
        initTitleBar(isShowBackIcon, titleContent, null);
    }


    public void initTitleBar(boolean isShowBackIcon, String titleContent, View.OnClickListener backOnClickListener) {
        initTitleBar(isShowBackIcon, titleContent, "", backOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent, String rightText, View.OnClickListener rightOnClickListener) {
        initTitleBar(isShowBackIcon, titleContent, rightText, -1, IconLocationType.LEFT, null, rightOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent, String rightText, int rightIcon, View.OnClickListener rightOnClickListener) {
        initTitleBar(isShowBackIcon, titleContent, rightText, rightIcon, IconLocationType.LEFT, null, rightOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent, String rightText, int rightTextColor, int rightIcon, int titleBg, View.OnClickListener rightOnClickListener) {
        initTitle(isShowBackIcon, titleContent, rightText, rightTextColor, rightIcon, IconLocationType.LEFT, titleBg, null, rightOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent, String rightText, int rightIcon, IconLocationType rightIconLoation, View.OnClickListener backOnClickListener, View.OnClickListener rightOnClickListener) {
        initTitle(isShowBackIcon, titleContent, rightText, -1, rightIcon, rightIconLoation, -1, backOnClickListener, rightOnClickListener);
    }

    public void initTitleBar(boolean isShowBackIcon, String titleContent, String rightText, int rightIcon, IconLocationType rightIconLoation, View.OnClickListener rightOnClickListener) {
        initTitle(isShowBackIcon, titleContent, rightText, -1, rightIcon, rightIconLoation, -1, null, rightOnClickListener);
    }


    public void initTitleBarFirSave(boolean isShowBackIcon, String titleContent, String rightText, View.OnClickListener rightOnClickListener) {
        initTitleBarSave(isShowBackIcon, titleContent, rightText, -1, IconLocationType.LEFT, null, rightOnClickListener);
    }

    public void initTitleBarSave(boolean isShowBackIcon, String titleContent, String rightText, int rightIcon, IconLocationType rightIconLoation, View.OnClickListener backOnClickListener, View.OnClickListener rightOnClickListener) {
        initTitle(isShowBackIcon, titleContent, rightText, R.color.color_4286fe, rightIcon, rightIconLoation, -1, backOnClickListener, rightOnClickListener);
    }

    /**
     * 默认显示back按钮
     *
     * @param titleContent
     * @param rightText
     * @param rightTextColor
     * @param rightOnClickListener
     */
    public void initTitleBar(String titleContent, String rightText, int rightTextColor, View.OnClickListener rightOnClickListener) {
        ActionBar actionBar = getSupportActionBar();
        if (!actionBar.isShowing()) {
            actionBar.show();
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.common_title_layout);
        actionBar.setElevation(0);
        View customView = getSupportActionBar().getCustomView();
        TextView contentTv = customView.findViewById(R.id.common_title_content);
        contentTv.setText(titleContent);
        ImageView backImg = customView.findViewById(R.id.common_title_back);
        backImg.setVisibility(View.VISIBLE);
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.title_bg));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView rightTv = customView.findViewById(R.id.common_title_right);
        if (rightTextColor != -1) {
            rightTv.setTextColor(getResources().getColor(rightTextColor));
        } else {
            rightTv.setTextColor(getResources().getColor(R.color.color_666666));
        }
        rightTv.setText(rightText);
        if (rightOnClickListener != null) {
            rightTv.setOnClickListener(rightOnClickListener);
        }
    }

    private void initTitle(boolean isShowBackIcon, String titleContent, String rightText, int rightTextColor, int rightIcon, IconLocationType rightIconLoation, int titleBg, final View.OnClickListener backOnClickListener, final View.OnClickListener rightOnClickListener) {
        ActionBar actionBar = getSupportActionBar();
//        int height = actionBar.getHeight();
//        Log.e("tag_height", "" + height);
        if (!actionBar.isShowing()) {
            actionBar.show();
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.common_title_layout);
        actionBar.setElevation(0);


       /* if (titleBg != -1) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(titleBg));
        } else {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.title_bg));
        }*/
        View customView = getSupportActionBar().getCustomView();
        TextView contentTv = customView.findViewById(R.id.common_title_content);
        contentTv.setText(titleContent);
        ImageView backImg = customView.findViewById(R.id.common_title_back);
        if (rightIcon == -99) {
            backImg.setImageResource(R.mipmap.ico_back_top_baise);
            contentTv.setTextColor(getResources().getColor(R.color.white));
            rightIcon = -1;
        }
        backImg.setVisibility(isShowBackIcon ? View.VISIBLE : View.GONE);
        if (backOnClickListener != null) {
            backImg.setOnClickListener(backOnClickListener);
        } else {
            backImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        TextView rightTv = customView.findViewById(R.id.common_title_right);
        if (rightTextColor != -1) {
            rightTv.setTextColor(getResources().getColor(rightTextColor));
        } else {
            rightTv.setTextColor(getResources().getColor(R.color.color_666666));
        }
        rightTv.setText(rightText);
        if (rightIcon != -1) {
            Drawable drawable = getResources().getDrawable(rightIcon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            switch (rightIconLoation) {
                case LEFT:
                    rightTv.setCompoundDrawables(drawable, null, null, null);
                    break;
                case RIGHT:
                    rightTv.setCompoundDrawables(null, null, drawable, null);
                    break;
                case TOP:
                    rightTv.setCompoundDrawables(null, drawable, null, null);
                    break;
                case BOTTOM:
                    rightTv.setCompoundDrawables(null, null, null, drawable);
                    break;
            }
        }
        if (rightOnClickListener != null) {
            rightTv.setOnClickListener(rightOnClickListener);
        }
    }

    /**
     * 页面渲染
     */
    public abstract void initView();

    /**
     * 获取数据
     */
    public abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ImageLoaderUtil.clearCache();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
    }
}
