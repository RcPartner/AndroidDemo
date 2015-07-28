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
                new String[]{"test", "test", "test", "test", "test", "test", "test", "test",
                        "test", "test", "test", "test", "test", "test", "test", "test",
                        "test", "test", "test", "test", "test", "test", "test", "test",
                        "test", "test", "test", "test", "test", "test", "test", "test"}));
    }


}
