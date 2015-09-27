package com.rc.androiddemo.ui.dialog;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-23 11:00
 */
public abstract class BaseFilterListAdapter<T> extends BaseAdapter {

    private Context mContext;

    private List<T> lists;

    private ListView lvList;

    public BaseFilterListAdapter(Context mContext, List<T> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView.setSelected(lvList.getCheckedItemPositions().get(position));
        Log.d(BaseFilterListAdapter.class.getSimpleName(), "position:" + position + ",isSelected: " + lvList.getCheckedItemPositions().get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists == null ? null : lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public ListView getLvList() {
        return lvList;
    }

    public void setLvList(ListView lvList) {
        this.lvList = lvList;
    }
}
