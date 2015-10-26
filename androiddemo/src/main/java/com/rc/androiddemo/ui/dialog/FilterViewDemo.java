package com.rc.androiddemo.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put(0, 1);
        arrayMap.put(0, 2);
        Toast.makeText(this, arrayMap.toString(), Toast.LENGTH_SHORT).show();
        ((FilterTitleView) findViewById(R.id.ftvDemo)).setItemClickListener(
                new FilterTitleView.OnItemClickListener() {
                    @Override
                    public void onClick(int position, View view) {
                        if (position == 1) {
                            getPopupWindow(view);
                        } else if (position == 2) {
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
        final SingleListPopupWindow w = new SingleListPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        w.setAdapter(new MyAdapter(this, list));
        w.setReset(contentView.findViewById(R.id.vReset));
        w.setConfirm(contentView.findViewById(R.id.vConfirm));
        w.setOnSingleSelected(new SingleListPopupWindow.OnSingleSelected() {
            @Override
            public void onSingleSelected(int position) {
                Toast.makeText(FilterViewDemo.this, String.valueOf(w.getAdapter().getLists().get(position)), Toast.LENGTH_SHORT).show();
            }
        });
        w.setOnMultiSelected(new SingleListPopupWindow.OnMultiSelected() {
            @Override
            public void onMultiSelected(List<Integer> selectedList) {
                for (int i = 0; i < w.getAdapter().getLists().size(); i++) {
                    Toast.makeText(FilterViewDemo.this, String.valueOf(w.getAdapter().getLists().get(i)), Toast.LENGTH_SHORT).show();
                }
            }

        });
        w.showAsDropDown(v);
    }

    private void getDoubleListPopupWindow(View v) {
        View contentView = getLayoutInflater().inflate(R.layout.popup_double_list, null);
        final DoubleListPopupWindow w = new DoubleListPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        w.setReset(contentView.findViewById(R.id.vReset));
        w.setConfirm(contentView.findViewById(R.id.vConfirm));
        w.setLvSecond((ListView) contentView.findViewById(R.id.lvSecond));
        w.setLeftAdapter(new MyAdapter(this, list));
//        w.setDataList(list2);
        w.setAdapter(new MyAdapter(this));
        w.setOnMultiSelected(new DoubleListPopupWindow.OnMultiSelected() {
            @Override
            public void onMultiSelected(List<ArrayMap<Integer, Integer>> selectedArray) {
                Toast.makeText(FilterViewDemo.this, selectedArray.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        w.setOnLeftListItemClickListener(new DoubleListPopupWindow.OnLeftListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((MyAdapter) w.getAdapter()).setLists(list2.get(position));
//                w.refreshRightList(position);
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
