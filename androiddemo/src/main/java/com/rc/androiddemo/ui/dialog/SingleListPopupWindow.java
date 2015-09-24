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
 * Date: 2015-09-23 09:45
 */
public class SingleListPopupWindow<T> extends PopupWindow{

    private ListView lvMenu;

    private View vReset;

    private View vConfirm;

    private boolean isMultiSelect;

    private BaseFilterListAdapter<T> adapter;

    private SingleSelectedCallBack<T> singleSelectedCallBack;

    private MultiSelectedCallBack<T> multiSelectedCallBack;

    public SingleListPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public SingleListPopupWindow(View contentView, int width, int height, final boolean isMultiSelect) {
        super(contentView, width, height, true);
        if (contentView == null) {
            throw new IllegalArgumentException("contentView must not be null");
        }
        lvMenu = (ListView) contentView.findViewById(android.R.id.list);
        if (lvMenu == null) {
            throw new IllegalArgumentException("contentView must contain a listView which id is android.R.id.list");
        }
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);

//        setAnimationStyle(R.style.DefaultStyle);
        this.isMultiSelect = isMultiSelect;
        if (isMultiSelect) {
            initMultiSelectMode();
        } else {
            initSingleSelectMode();
        }
    }

    private void initSingleSelectMode() {
        lvMenu.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.notifyDataSetChanged();
                if (!isMultiSelect) {
                    T data = getSingleSelectedData(position);
                    if (singleSelectedCallBack != null) {
                        singleSelectedCallBack.selected(position, data);
                    }
                    dismiss();
                }
            }
        });
    }

    private void initMultiSelectMode() {
        lvMenu.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        vReset = getContentView().findViewById(R.id.vReset);
        vConfirm = getContentView().findViewById(R.id.vConfirm);
        if (vReset == null || vConfirm == null) {
            throw new IllegalArgumentException("In MultiSelect Mode, your contentView must contain vReset and vConfirm");
        }
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getContentView().requestLayout();
                getContentView().invalidate();
            }
        });
        vReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvMenu.getCheckedItemPositions().clear();
                adapter.notifyDataSetChanged();
            }
        });
        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (multiSelectedCallBack != null) {
                    multiSelectedCallBack.multiSelected(getMultiSelectedData());
                }
                dismiss();
            }
        });
    }

    private T getSingleSelectedData(int position) {
        return adapter.getLists().get(position);
    }

    private List<T> getMultiSelectedData() {
        List<T> dataList = new ArrayList<>();
        for (int i = 0; i < lvMenu.getCheckedItemPositions().size(); i++) {
            int key = lvMenu.getCheckedItemPositions().keyAt(i);
            if (lvMenu.getCheckedItemPositions().get(key)) {
                dataList.add(adapter.getLists().get(key));
            }
        }
        return dataList;
    }

    public void setAdapter(BaseFilterListAdapter<T> adapter) {
        this.adapter = adapter;
        if (this.adapter != null) {
            adapter.setSparseBooleanArray(lvMenu.getCheckedItemPositions());
        }
        lvMenu.setAdapter(this.adapter);
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
