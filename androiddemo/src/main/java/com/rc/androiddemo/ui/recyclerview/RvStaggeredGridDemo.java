package com.rc.androiddemo.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 11:35
 */
public class RvStaggeredGridDemo extends RcListDemo {

    private RecyclerView.LayoutManager lmStaggeredGrid;
    private RvStaggeredGridAdapter rvStaggeredGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lmStaggeredGrid = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rcList.setLayoutManager(lmStaggeredGrid);

        rvStaggeredGridAdapter = new RvStaggeredGridAdapter(list);
        rcList.setAdapter(rvStaggeredGridAdapter);

    }
}
