package com.rc.androiddemo.ui.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-25 10:37
 */
public abstract class BaseTagAdapter<T> {

    private List<T> dataList;

    private List<Observer> observerList;

    public BaseTagAdapter() {
        observerList = new ArrayList<>();
    }

    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void notifyChanged() {
        update();
    }

    private void update() {
        for (Observer o : observerList) {
            o.updateView();
        }
    }

    public void registerObserver(Observer observer) {
        if (observerList.contains(observer)) {
            return;
        }
        observerList.add(observer);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    abstract public T getItem(int position);

    abstract long getItemId(int position);

    abstract public View getView(FlowLayout flowLayout, int position, T data);

    public interface Observer {
        void updateView();
    }
}
