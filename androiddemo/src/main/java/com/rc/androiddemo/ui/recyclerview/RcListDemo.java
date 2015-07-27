package com.rc.androiddemo.ui.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 10:39
 */
public class RcListDemo extends Activity {

    private RecyclerView rcList;

    private RcListAdapter rcListAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_list_demo);
        rcList = (RecyclerView) findViewById(R.id.rcList);

        layoutManager = new LinearLayoutManager(this);
        rcList.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("第" + i + "项");
        }
        rcListAdapter = new RcListAdapter(list);
        rcList.setAdapter(rcListAdapter);


    }
}
