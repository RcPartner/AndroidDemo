package com.rc.androiddemo.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 11:22
 */
public class RcGridDemo extends RcListDemo {

    private RecyclerView.LayoutManager lmGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lmGrid = new GridLayoutManager(this, 4);
        rcList.setLayoutManager(lmGrid);
    }
}
