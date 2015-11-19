package com.rc.androiddemo.ui.pulltorefresh;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-12 15:06
 */
public interface IPullToRefreshCallBack {
    void pullOffsetPercent(float percent);

    void refreshing();

    void refreshCompleted();
}
