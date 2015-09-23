package com.rc.androiddemo.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_view);
        initList();


        ((FilterTitleView) findViewById(R.id.ftvDemo)).setItemClickListener(
                new FilterTitleView.OnItemClickListener() {
                    @Override
                    public void onClick(int position, View view) {
                        getPopupWindow(view);
                    }
                });
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            list.add("第: " + i + "  行数据");
        }
    }

    private void getPopupWindow(View v) {
        View contentView = getLayoutInflater().inflate(R.layout.popup_single_list, null);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SingleListPopupWindow<String> w = new SingleListPopupWindow<String>(contentView, dm.widthPixels, dm.heightPixels / 2, true);
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
            }
        });
        w.showAsDropDown(v);
    }

    private class MyAdapter extends BaseFilterListAdapter<String> {

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
            convertView = getLayoutInflater().inflate(R.layout.item_list, null);
            ((TextView) convertView).setText(getLists().get(position));
            return super.getView(position, convertView, parent);
        }
    }
}
