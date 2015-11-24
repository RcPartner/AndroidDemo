package com.rc.androiddemo.ui.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-11-19 16:52
 */
public class SimpleHeaderView implements IPullToRefreshCallBack, ICustomRefreshView {

    private Context mContext;

    private View vRoot;

    private LinearLayout llBeforeLoading;

    private TextView tvTest;

    private ProgressBar pbProgress;

    private ProgressBar pbLoading;

    private TextView tvComplete;

    public SimpleHeaderView(Context mContext, ViewGroup parent) {
        this.mContext = mContext;
        vRoot = LayoutInflater.from(mContext).inflate(R.layout.pull_to_refresh_view, parent, false);
        llBeforeLoading = (LinearLayout) vRoot.findViewById(R.id.llBeforeLoading);
        tvTest = (TextView) vRoot.findViewById(R.id.tvTest);
        pbProgress = (ProgressBar) vRoot.findViewById(R.id.pbProgress);
        pbLoading = (ProgressBar) vRoot.findViewById(R.id.pbLoading);
        tvComplete = (TextView) vRoot.findViewById(R.id.tvComplete);
    }

    @Override
    public int getViewHeight() {
        return vRoot.getMeasuredHeight();
    }

    @Override
    public View getView() {
        return vRoot;
    }

    @Override
    public void pullOffsetPercent(float percent) {
        if (llBeforeLoading.getVisibility() == View.GONE) {
            llBeforeLoading.setVisibility(View.VISIBLE);
            tvComplete.setVisibility(View.GONE);
            pbLoading.setVisibility(View.GONE);
        }
        if (percent < 1) {
            tvTest.setText("拉动刷新");
            pbProgress.setProgress((int) (percent * 100));
        } else {
            tvTest.setText("释放刷新");
        }
    }

    @Override
    public void refreshing() {
        llBeforeLoading.setVisibility(View.GONE);
        tvComplete.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshCompleted() {
        llBeforeLoading.setVisibility(View.GONE);
        tvComplete.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }
}
