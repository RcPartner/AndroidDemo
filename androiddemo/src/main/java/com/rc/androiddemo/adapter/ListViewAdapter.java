package com.rc.androiddemo.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 *
 * @Description 列表适配器
 * @Author czm
 * @Version V0.1
 * @CreateDate 2014-11-5 上午11:30:55
 * @ModifyAuthor
 * @ModifyDescri
 * @ModifyDate
 * @param <T>
 */
public abstract class ListViewAdapter<T> extends BaseAdapter {

	private List<T> list;

	public ListViewAdapter(List<T> list)
	{
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
		notifyDataSetInvalidated();
	}



}
