package com.rc.androiddemo.ui.dialog;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-28 10:41
 */
public abstract class BaseFilterPopupWindow extends PopupWindow {

    protected ListView lvData;

    protected View vReset;

    protected View vConfirm;

    protected boolean isMultiSelect;

    protected BaseFilterListAdapter adapter;

    public BaseFilterPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public BaseFilterPopupWindow(View contentView, int width, int height, boolean isMultiSelect) {
        super(contentView, width, height, true);
        this.isMultiSelect = isMultiSelect;

        initView(contentView);
        initMode();
    }

    protected void initView(View contentView) {
        if (contentView == null) {
            throw new IllegalArgumentException("contentView must not be null");
        }
        lvData = (ListView) contentView.findViewById(android.R.id.list);
        if (lvData == null) {
            throw new IllegalArgumentException("contentView must have a listView whose id attribute is android.R.id.list");
        }
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);

        setAnimationStyle(R.style.DefaultStyle);
    }

    protected void initMode() {
        if (isMultiSelect) {
            initMultiSelectMode();
        } else {
            initSingleSelectMode();
        }
    }

    protected void initSingleSelectMode() {
        lvData.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                singleSelected(position);
                adapter.notifyDataSetChanged();
                dismiss();
            }
        });
    }

    protected void singleSelected(int position) {

    }

    protected void initMultiSelectMode() {
        lvData.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                multiSelect(position);
                if (adapter == null) {
                    throw new RuntimeException("adapter is null, have you invoke setAdapter() ?");
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void setvReset(View vReset) {
        if (vReset == null) {
            throw new IllegalArgumentException("vReset must not be null");
        }
        this.vReset = vReset;
        vReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void setvConfirm(View vConfirm) {
        if (vConfirm == null) {
            throw new IllegalArgumentException("vConfirm must not be null");
        }
        this.vConfirm = vConfirm;
        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    protected void multiSelect(int position) {

    }

    protected void reset() {

    }

    protected void confirm() {

    }

    public boolean isMultiSelect() {
        return isMultiSelect;
    }

    public void setIsMultiSelect(boolean isMultiSelect) {
        this.isMultiSelect = isMultiSelect;
        initMode();
    }

    public BaseFilterListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseFilterListAdapter adapter) {
        this.adapter = adapter;
        lvData.setAdapter(this.adapter);
        if (this.adapter != null) {
            adapter.setSparseBooleanArray(lvData.getCheckedItemPositions());
        }
    }

}
