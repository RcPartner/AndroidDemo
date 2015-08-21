package com.rc.androiddemo.ui.pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-24 14:44
 */
public class PullToRefreshDemo extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

        PullToRefreshView ptrvTest = (PullToRefreshView) findViewById(R.id.ptrvTest);
        ListView lv = (ListView) findViewById(R.id.lvContent);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1,
                new String[]{"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8",
                        "test9", "test10", "test11", "test12", "test13", "test14", "test15", "test16",
                        "test17", "test18", "test19", "test20", "test21", "test22", "test23", "test24",
                        "test25", "test26", "test27", "test28", "test29", "test30", "test31", "test32"}));
    }


}
