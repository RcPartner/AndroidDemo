package com.rc.androiddemo.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-23 15:21
 */
public class FilterViewDemo extends Activity {

    private List<String> list = new ArrayList<>();

    private ArrayMap<Integer, List<String>> list2 = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_view);
        initList();


        ((FilterTitleView) findViewById(R.id.ftvDemo)).setItemClickListener(
                new FilterTitleView.OnItemClickListener() {
                    @Override
                    public void onClick(int position, View view) {
                        if (position == 1) {
                            getPopupWindow(view);
                        }else if (position == 2) {
                            getDoubleListPopupWindow(view);
                        }
                    }
                });
    }

    private void initList() {
        for (int i = 0; i < 5; i++) {
            list.add("第: " + i + "  行数据");
        }

        for (int i = 0; i < 5; i++) {
            List<String> l = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                l.add("第: " + i + "," + j + "  个数据");
            }
            list2.put(i, l);
        }
    }

    private void getPopupWindow(View v) {
        View contentView = getLayoutInflater().inflate(R.layout.popup_single_list, null);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final SingleListPopupWindow<String> w = new SingleListPopupWindow<String>(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        w.setAdapter(new MyAdapter(this, list));
        w.setSingleSelectedCallBack(new SingleListPopupWindow.SingleSelectedCallBack<String>() {
            @Override
            public void selected(int position, String data) {
                Toast.makeText(FilterViewDemo.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        w.setMultiSelectedCallBack(new SingleListPopupWindow.MultiSelectedCallBack<String>() {
            @Override
            public void multiSelected(List<String> data) {
                for (int i = 0; i < data.size(); i++) {
                    Toast.makeText(FilterViewDemo.this, data.get(i), Toast.LENGTH_SHORT).show();
                }
//                w.setAdapter(new MyAdapter(FilterViewDemo.this, list2));
//                w.getContentView().requestLayout();
            }
        });
        w.showAsDropDown(v);
    }

    private void getDoubleListPopupWindow(View v) {
        View contentView = getLayoutInflater().inflate(R.layout.popup_double_list, null);
        final DoubleListPopupWindow<String, String> w = new DoubleListPopupWindow<>(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        w.setLeftAdapter(new MyAdapter(this, list));
        w.setDataList(list2);
        w.setAdapter(new MyAdapter(this));
        w.setSingleSelectedCallBack(new SingleListPopupWindow.SingleSelectedCallBack<String>() {
            @Override
            public void selected(int position, String data) {
                Toast.makeText(FilterViewDemo.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        w.setMultiSelectedCallBack(new SingleListPopupWindow.MultiSelectedCallBack<String>() {
            @Override
            public void multiSelected(List<String> data) {
                for (int i = 0; i < data.size(); i++) {
                    Toast.makeText(FilterViewDemo.this, data.get(i), Toast.LENGTH_SHORT).show();
                }
//                w.setAdapter(new MyAdapter(FilterViewDemo.this, list2));
//                w.getContentView().requestLayout();
            }
        });
        w.showAsDropDown(v);
    }

    private class MyAdapter extends BaseFilterListAdapter<String> {

        public MyAdapter(Context mContext) {
            super(mContext);
        }

        public MyAdapter(Context mContext, List<String> lists) {
            super(mContext, lists);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_list, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tvTest);
            tv.setText(getLists().get(position));
            tv.setSelected(getSparseBooleanArray().get(position));
            return convertView;
        }
    }
}
