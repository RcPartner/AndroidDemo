package com.rc.androiddemo.ui.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-20 17:54
 */
public class PullUpOrDownController implements IPullUpOrDownController {

    @Override
    public boolean canPullUp(ViewGroup parent, View content) {

        return false;
    }

    @Override
    public boolean canPullDown(ViewGroup parent, View content) {
        if (content instanceof AbsListView) {
            AbsListView absListView = (AbsListView) content;
            return !(absListView.getChildCount() > 0 && absListView.getFirstVisiblePosition() > 0);
        }
        if (content instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) content;
            return !(viewGroup.getChildCount() > 0 && viewGroup.getChildAt(0).getTop() > 0);
        }
        return false;
    }
}
