package com.rc.androiddemo.ui.recyclerview;

import android.view.ViewGroup;

import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 11:38
 */
public class RvStaggeredGridAdapter extends RcListAdapter {

    public RvStaggeredGridAdapter(List<String> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(RcListHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        ViewGroup.LayoutParams layoutParams = holder.tvName.getLayoutParams();
        Random random = new Random();
        layoutParams.width = 100 + random.nextInt(300);
        layoutParams.height = 300 + random.nextInt(300);
       // holder.tvName.setLayoutParams(layoutParams);
    }

}
