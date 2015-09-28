package com.rc.androiddemo.ui.dialog;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-28 15:38
 */
public class DoubleListPopupWindow<V, T> extends BaseFilterPopupWindow<T> {

    private ListView lvSecond;

    private SparseArray<SparseBooleanArray> selectArray;

    private ArrayMap<Integer, List<T>> dataList;

    private BaseFilterListAdapter<V> leftAdapter;

    private OnLeftListItemClickListener onLeftListItemClickListener;

    public DoubleListPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public DoubleListPopupWindow(View contentView, int width, int height, boolean isMultiSelect) {
        super(contentView, width, height, isMultiSelect);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        dataList = new ArrayMap<>();
        selectArray = new SparseArray<>();

        lvSecond = (ListView) contentView.findViewById(R.id.lvSecond);
        if (lvSecond == null) {
            throw new IllegalArgumentException("contentView must have a ListView whose id attribute is \"lvSecond\"");
        }

        lvSecond.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lvSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        if (adapter != null) {
            //从存储每个子列表选中状态的list中取出状态列表，如果为null则新建一个并存入list中
            SparseBooleanArray s;
            if ((s = selectArray.get(position, null)) == null) {
                s = new SparseBooleanArray();
                selectArray.put(position, s);
            }
            //更新适配器的属性
            adapter.setSparseBooleanArray(s);
            adapter.setLists(dataList.get(position));
            adapter.notifyDataSetInvalidated();
        } else {
            throw new RuntimeException("adapter is null, have you invoke setLeftAdapter() ?");
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
    protected List<T> getMultiSelectedData() {
        List<T> data = new ArrayList<>();
        for (int i = 0; i < selectArray.size(); i++) {
            SparseBooleanArray s = selectArray.get(selectArray.keyAt(i));
            for (int j = 0; j  < s.size(); j ++) {
                if (s.get(s.keyAt(j))) {
                    data.add(dataList.get(selectArray.keyAt(i)).get(s.keyAt(j)));
                }
            }
        }
        return data;
    }

    @Override
    protected void reset() {
        super.reset();
        for (int i = 0; i < selectArray.size(); i++) {
            selectArray.get(selectArray.keyAt(i)).clear();
        }
        adapter.notifyDataSetChanged();
    }

    public BaseFilterListAdapter<V> getLeftAdapter() {
        return leftAdapter;
    }

    public void setLeftAdapter(BaseFilterListAdapter<V> leftAdapter) {
        this.leftAdapter = leftAdapter;
        lvSecond.setAdapter(leftAdapter);
        if (leftAdapter != null) {
            this.leftAdapter.setSparseBooleanArray(lvSecond.getCheckedItemPositions());
        }
    }

    public BaseFilterListAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseFilterListAdapter<T> adapter) {
        this.adapter = adapter;
        lvData.setAdapter(adapter);
    }

    public ArrayMap<Integer, List<T>> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayMap<Integer, List<T>> dataList) {
        this.dataList = dataList;
    }

    public void setOnLeftListItemClickListener(OnLeftListItemClickListener onLeftListItemClickListener) {
        this.onLeftListItemClickListener = onLeftListItemClickListener;
    }

    public interface OnLeftListItemClickListener {
        void onItemClick(int position);
    }
}
