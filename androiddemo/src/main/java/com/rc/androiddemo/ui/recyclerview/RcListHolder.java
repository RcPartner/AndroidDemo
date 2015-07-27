package com.rc.androiddemo.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 10:44
 */
public class RcListHolder extends RecyclerView.ViewHolder{

    TextView tvName;

    public RcListHolder(View itemView) {
        super(itemView);
        tvName = (TextView)itemView.findViewById(R.id.tvName);
    }
}
