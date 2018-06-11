package com.lyldemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.rxretrofitdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by KJT-30 on 2018/6/4.
 */

public class LoginActivity extends AppCompatActivity {
    private TextView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Log.e("onCreate", "onCreate22222");
        view = (TextView) findViewById(R.id.tv2);
        EventBus.getDefault().register(this);
        getEventData();
    }

    public void getEventData() throws EventBusException {
        Log.e("Event3333", "33333:" + EventBus.getDefault().getStickyEvent(String.class));
        String result = EventBus.getDefault().getStickyEvent(String.class);
        view.setText(result);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(String result) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "onDestroy222222");
        EventBus.getDefault().unregister(this);
    }
}
