package com.rc.androiddemo.ui.dialog;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-23 09:45
 */
public class SingleListPopupWindow<T> extends BaseFilterPopupWindow<T> {

    public SingleListPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public SingleListPopupWindow(View contentView, int width, int height, boolean isMultiSelect) {
        super(contentView, width, height, isMultiSelect);
    }

    @Override
    protected void reset() {
        super.reset();
        lvData.getCheckedItemPositions().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected List<T> getMultiSelectedData() {
        List<T> dataList = new ArrayList<>();
        for (int i = 0; i < lvData.getCheckedItemPositions().size(); i++) {
            int key = lvData.getCheckedItemPositions().keyAt(i);
            if (lvData.getCheckedItemPositions().get(key)) {
                dataList.add(adapter.getLists().get(key));
            }
        }
        return dataList;
    }

    public void setAdapter(BaseFilterListAdapter<T> adapter) {
        this.adapter = adapter;
        lvData.setAdapter(this.adapter);
        if (this.adapter != null) {
            adapter.setSparseBooleanArray(lvData.getCheckedItemPositions());
        }
    }
}
