package com.rc.androiddemo.ui.dialog;

import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-23 09:45
 */
public class SingleListPopupWindow extends BaseFilterPopupWindow {

    private OnSingleSelected onSingleSelected;

    private OnMultiSelected onMultiSelected;

    public SingleListPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public SingleListPopupWindow(View contentView, int width, int height, boolean isMultiSelect) {
        super(contentView, width, height, isMultiSelect);
    }

    @Override
    protected void singleSelected(int position) {
        super.singleSelected(position);
        if (onSingleSelected != null) {
            onSingleSelected.onSingleSelected(position);
        }
    }

    @Override
    protected void reset() {
        super.reset();
        lvData.getCheckedItemPositions().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void confirm() {
        super.confirm();
        if (onMultiSelected != null) {
            onMultiSelected.onMultiSelected(getMultiSelectedData());
        }
        dismiss();
    }

    protected List<Integer> getMultiSelectedData() {
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < lvData.getCheckedItemPositions().size(); i++) {
            int key = lvData.getCheckedItemPositions().keyAt(i);
            if (lvData.getCheckedItemPositions().get(key)) {
                set.add(key);
            }
        }
        return set;
    }

    public void setOnSingleSelected(OnSingleSelected onSingleSelected) {
        this.onSingleSelected = onSingleSelected;
    }

    public void setOnMultiSelected(OnMultiSelected onMultiSelected) {
        this.onMultiSelected = onMultiSelected;
    }

    public interface OnSingleSelected {
        void onSingleSelected(int position);
    }

    public interface OnMultiSelected {
        void onMultiSelected(List<Integer> selectedList);
    }
}
