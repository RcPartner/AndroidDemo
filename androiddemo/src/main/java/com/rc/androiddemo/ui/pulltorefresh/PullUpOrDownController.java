package com.rc.androiddemo.ui.pulltorefresh;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-20 17:54
 */
public class PullUpOrDownController implements IPullUpOrDownController {

    @Override
    public boolean canPullUp(ViewGroup parent, View content) {
        if (content instanceof AbsListView) {
            AbsListView absListView = (AbsListView) content;
            Rect rect = new Rect();
            View lastView = absListView.getChildAt(absListView.getLastVisiblePosition() -
                    absListView.getFirstVisiblePosition());
            lastView.getGlobalVisibleRect(rect);
            if (absListView.getChildCount() > 0 && absListView.getLastVisiblePosition() ==
                    absListView.getAdapter().getCount() - 1 && lastView.getMeasuredHeight() ==
                    rect.bottom - rect.top) {
                Log.d("LastView Height: ", rect.height() + "true");
            } else {
                Log.d("LastView Height: ", rect.height() + "false");
            }
            return absListView.getChildCount() > 0 &&  absListView.getLastVisiblePosition() ==
                    absListView.getAdapter().getCount() - 1 && lastView.getMeasuredHeight() ==
                    rect.bottom - rect.top;
        } else if (content instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) content;
            View childView = scrollView.getChildAt(0);
            return scrollView.getScrollY() + childView.getHeight() == childView.getMeasuredHeight();
        }else if (content instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) content;
            Rect rect = new Rect();
            viewGroup.getGlobalVisibleRect(rect);
            return viewGroup.getScrollY() >= (viewGroup.getMeasuredHeight() -
                    rect.height() -
                    viewGroup.getPaddingTop() -
                    viewGroup.getPaddingBottom());
        }

        return false;
    }

    @Override
    public boolean canPullDown(ViewGroup parent, View content) {
        if (content instanceof AbsListView) {
            AbsListView absListView = (AbsListView) content;
            return absListView.getChildCount() > 0 && absListView.getFirstVisiblePosition() == 0 &&
                    absListView.getChildAt(0).getTop() >= 0;
        } else if (content instanceof ScrollView) {
            return content.getScrollY() == 0;
        } else if (content instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) content;
            return viewGroup.getChildCount() > 0 && viewGroup.getChildAt(0).getTop() >= 0;
        }
        return false;
    }
}
