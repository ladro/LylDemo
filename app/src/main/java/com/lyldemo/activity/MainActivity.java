package com.lyldemo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lyldemo.base.BaseActivity;
import com.lyldemo.base.BaseApp;
import com.lyldemo.entity.TypeInfo;
import com.lyldemo.retrofit.entity.RetrofitResultData;
import com.rxretrofitdemo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private List<TypeInfo> list = new ArrayList<>();
    @ViewInject(R.id.list)
    private ListView listView;
    private String[] typeList;

    @Override
    public void initView() {

        initTitleBar(true, "我的功能demo合集");

        typeList = getResources().getStringArray(R.array
                .chedan_type);
        MyAdapter adapter = new MyAdapter(MainActivity.this, typeList);
        listView.setAdapter(adapter);

    }
    @TargetApi(19)
    @Event(value = {}, type = View.OnClickListener.class)
    private void ViewClick(View view) {
        switch (view.getId()) {
//            case R.id.tv1:
//                EventBus.getDefault().postSticky("sasasasas");
//                IntentUtil.redirectToNextActivity(MainActivity.this, ActivityTwo.class);
//                break;
        }
    }

    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private String[] typeList;

        public MyAdapter(Context context, String[] typeList) {
            this.mContext = context;
            this.typeList = typeList;
        }

        @Override
        public int getCount() {
            return typeList.length;
        }

        @Override
        public Object getItem(int arg0) {
            return typeList[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int arg0, View arg1, ViewGroup arg2) {
            ViewHolder holder;
            if (arg1 == null) {
                arg1 = View.inflate(mContext, R.layout.item_popup_layout,
                        null);
                holder = new ViewHolder();
                holder.name = arg1.findViewById(R.id.tv_name);
                arg1.setTag(holder);
            } else {
                holder = (ViewHolder) arg1.getTag();
            }
            holder.name.setText(typeList[arg0]);
            return arg1;
        }

        class ViewHolder {
            TextView name;
        }
    }

    public void getData() {
        BaseApp.instance().
                getApiNetService().getShipTypeList("").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new DefaultObserver<Response<RetrofitResultData<List<TypeInfo>>>>() {
                    @Override
                    public void onNext(Response<RetrofitResultData<List<TypeInfo>>> value) {
                        Log.e("tag_onNext", "tag_onNext:" + value.body().getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag_onError", "tag_onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag_onComplete", "tag_onComplete:");
                    }
                });
    }

}
