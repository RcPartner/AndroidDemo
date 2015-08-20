package com.rc.androiddemo.ui.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-20 16:54
 */
public interface IPullUpOrDownController {
    boolean canPullUp(ViewGroup parent, View content);

    boolean canPullDown(ViewGroup parent, View content);
}
