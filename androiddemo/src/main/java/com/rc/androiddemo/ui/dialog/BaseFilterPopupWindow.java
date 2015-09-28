package com.rc.androiddemo.ui.dialog;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-28 10:41
 */
public abstract class BaseFilterPopupWindow<T> extends PopupWindow {

    protected ListView lvData;

    protected View vReset;

    protected View vConfirm;

    protected boolean isMultiSelect;

    protected BaseFilterListAdapter<T> adapter;

    protected SingleSelectedCallBack<T> singleSelectedCallBack;

    protected MultiSelectedCallBack<T> multiSelectedCallBack;

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
        ColorDrawable dw = new ColorDrawable(0xb0000000);
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
                adapter.notifyDataSetChanged();
                if (singleSelectedCallBack != null) {
                    T data = getSingleSelectedData(position);
                    singleSelectedCallBack.selected(position, data);
                }
                dismiss();
            }
        });
    }

    protected T getSingleSelectedData(int position) {
        return adapter.getLists().get(position);
    }

    protected void initMultiSelectMode() {
        lvData.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        vReset = getContentView().findViewById(R.id.vReset);
        vConfirm = getContentView().findViewById(R.id.vConfirm);
        if (vReset == null || vConfirm == null) {
            throw new IllegalArgumentException("In MultiSelect Mode, your contentView must have vReset and vConfirm");
        }
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
        vReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
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
        if (multiSelectedCallBack != null) {
            multiSelectedCallBack.multiSelected(getMultiSelectedData());
        }
        dismiss();
    }

    protected List<T> getMultiSelectedData() {
        return new ArrayList<>();
    }

    public void setMultiSelectedCallBack(MultiSelectedCallBack<T> multiSelectedCallBack) {
        this.multiSelectedCallBack = multiSelectedCallBack;
    }

    public void setSingleSelectedCallBack(SingleSelectedCallBack<T> singleSelectedCallBack) {
        this.singleSelectedCallBack = singleSelectedCallBack;
    }

    public interface SingleSelectedCallBack<T> {
        void selected(int position, T data);
    }

    public interface MultiSelectedCallBack<T> {
        void multiSelected(List<T> data);
    }
}
