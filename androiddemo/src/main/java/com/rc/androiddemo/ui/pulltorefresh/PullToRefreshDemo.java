package com.rc.androiddemo.ui.pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-24 14:44
 */
public class PullToRefreshDemo extends Activity {

    private MyAdapter adapter;

    private PullToRefreshView ptrvTest;

    private List<String> datas = new ArrayList<>(Arrays.asList((new String[]{"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8",
            "test9", "test10", "test11", "test12", "test13", "test14", "test15", "test16",
            "test17", "test18", "test19", "test20", "test21", "test22", "test23", "test24",
            "test25", "test26", "test27", "test28", "test29", "test30", "test31", "test32"})));

    private List<String> datas2 = new ArrayList<>(Arrays.asList((new String[]{"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8",
            "test9", "test10", "test11", "test12", "test13", "test14", "test15", "test16",
            "test17", "test18", "test19", "test20", "test21", "test22", "test23", "test24",
            "test25", "test26", "test27", "test28", "test29", "test30", "test31", "test32"})));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

        ptrvTest = (PullToRefreshView) findViewById(R.id.ptrvTest);
        ListView lv = (ListView) findViewById(R.id.lvContent);
        SimpleHeaderView view = new SimpleHeaderView(this, ptrvTest);
        SimpleHeaderView view2 = new SimpleHeaderView(this, ptrvTest);
        ptrvTest.setHeaderView(view);
        ptrvTest.setFooterView(view2);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);

        ptrvTest.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
        });

        ptrvTest.setOnLoadMoreListener(new PullToRefreshView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                addItem();
            }
        });
    }

    private void addItem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datas.add("新加item" + datas.size());
                        adapter.notifyDataSetChanged();
                        ptrvTest.loadMoreComplete();
                    }
                });
            }
        }).start();
    }

    private void refreshItem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datas = datas2;
                        adapter.notifyDataSetInvalidated();
                        ptrvTest.refreshComplete();
                    }
                });
            }
        }).start();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
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
            convertView = getLayoutInflater().inflate(R.layout.item_ptrvlist, null);
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(datas.get(position));
            return convertView;
        }
    }
}
