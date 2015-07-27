package com.rc.androiddemo.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rc.androiddemo.R;

import java.util.List;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 10:42
 */
public class RcListAdapter extends RecyclerView.Adapter<RcListHolder> {

    private List<String> list;

    public RcListAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RcListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_list, null);
        RcListHolder rcListHolder = new RcListHolder(view);
        return rcListHolder;
    }

    @Override
    public void onBindViewHolder(RcListHolder holder, int position) {
        holder.tvName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
}
