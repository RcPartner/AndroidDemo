package com.rc.androiddemo.ui.dialog;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-28 15:38
 */
public class DoubleListPopupWindow extends BaseFilterPopupWindow {

    private ListView lvSecond;

    private SparseArray<SparseBooleanArray> selectArray;

    private BaseFilterListAdapter leftAdapter;

    private OnLeftListItemClickListener onLeftListItemClickListener;

    private OnSelected onSelected;

    private OnMultiSelected onMultiSelected;

    public int currentLeftSelected;

    public DoubleListPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public DoubleListPopupWindow(View contentView, int width, int height, boolean isMultiSelect) {
        super(contentView, width, height, isMultiSelect);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        selectArray = new SparseArray<>();
    }

    public void setLvSecond(ListView lvSecond) {
        this.lvSecond = lvSecond;
        if (lvSecond == null) {
            throw new IllegalArgumentException("lvSecond must not be null");
        }

        lvSecond.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentLeftSelected = position;
                leftAdapter.notifyDataSetChanged();
                doLeftListClick(position);
            }
        });

    }

    /**
     * 点击左边列表时处理
     * @param position
     */
    private void doLeftListClick(int position) {
        if (onLeftListItemClickListener != null) {
            onLeftListItemClickListener.onItemClick(position);
        }
        refreshRightList(position);
    }

    public void refreshRightList(int position) {
        if (adapter != null) {
            //从存储每个子列表选中状态的list中取出状态列表，如果为null则新建一个并存入list中
            SparseBooleanArray s;
            if ((s = selectArray.get(position, null)) == null) {
                s = new SparseBooleanArray();
                selectArray.put(position, s);
            }
            //更新适配器的属性
            adapter.setSparseBooleanArray(s);
            adapter.notifyDataSetInvalidated();
        }
    }

    @Override
    protected void singleSelected(int position) {
        super.singleSelected(position);
        if (onSelected != null) {
            onSelected.onSelected(currentLeftSelected, position);
        }
    }

    @Override
    protected void multiSelect(int position) {
        super.multiSelect(position);
        boolean selected = adapter.getSparseBooleanArray().get(position, false);
        adapter.getSparseBooleanArray().put(position, !selected);
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

    private List<ArrayMap<Integer, Integer>>  getMultiSelectedData() {
        List<ArrayMap<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < selectArray.size(); i++) {
            SparseBooleanArray s = selectArray.get(selectArray.keyAt(i));
            for (int j = 0; j < s.size(); j++) {
                if (s.get(s.keyAt(j))) {
                    ArrayMap<Integer, Integer> data = new ArrayMap<>();
                    data.put(selectArray.keyAt(i), s.keyAt(j));
                    list.add(data);
//                    data.add(dataList.get(selectArray.keyAt(i)).get(s.keyAt(j)));
                }
            }
        }
        return list;
//        return data;
    }

    @Override
    protected void reset() {
        super.reset();
        for (int i = 0; i < selectArray.size(); i++) {
            selectArray.get(selectArray.keyAt(i)).clear();
        }
        adapter.notifyDataSetChanged();
    }

    public BaseFilterListAdapter getLeftAdapter() {
        return leftAdapter;
    }

    public void setLeftAdapter(BaseFilterListAdapter leftAdapter) {
        this.leftAdapter = leftAdapter;
        lvSecond.setAdapter(leftAdapter);
        if (leftAdapter != null) {
            this.leftAdapter.setSparseBooleanArray(lvSecond.getCheckedItemPositions());
        }
    }

    public ListView getLvSecond() {
        return lvSecond;
    }

    public void setOnSelected(OnSelected onSelected) {
        this.onSelected = onSelected;
    }

    public void setOnLeftListItemClickListener(OnLeftListItemClickListener onLeftListItemClickListener) {
        this.onLeftListItemClickListener = onLeftListItemClickListener;
    }

    public void setOnMultiSelected(OnMultiSelected onMultiSelected) {
        this.onMultiSelected = onMultiSelected;
    }

    public interface OnLeftListItemClickListener {
        void onItemClick(int position);
    }

    public interface OnSelected {
        void onSelected(int parentPos, int childPos);
    }

    public interface OnMultiSelected {
        void onMultiSelected(List<ArrayMap<Integer, Integer>> selectedArray);
    }
}
