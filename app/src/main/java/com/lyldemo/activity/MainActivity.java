package com.lyldemo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lyldemo.base.BaseActivity;
import com.lyldemo.entity.TypeInfo;
import com.lyldemo.utils.IntentUtil;
import com.rxretrofitdemo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
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
        listView.setOnItemClickListener(this);
    }

    @Override
    public void getData() {

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                IntentUtil.redirectToNextActivity(MainActivity.this, ActivityTwo.class);
                break;
            case 1:
                break;
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


}
