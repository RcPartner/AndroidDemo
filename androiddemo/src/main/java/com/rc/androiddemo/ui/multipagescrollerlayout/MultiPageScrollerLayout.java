package com.rc.androiddemo.ui.multipagescrollerlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.rc.androiddemo.ui.pulltorefresh.IPullUpOrDownController;
import com.rc.androiddemo.ui.pulltorefresh.PullUpOrDownController;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2016-01-04 17:19
 */
public class MultiPageScrollerLayout extends ViewGroup {

    private Scroller mScroller;

    private int currentPageIndex = 0;

    private IPullUpOrDownController pullUpOrDownController;

    public MultiPageScrollerLayout(Context context) {
        this(context, null);
    }

    public MultiPageScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiPageScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        pullUpOrDownController = new PullUpOrDownController();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec),
                MeasureSpec.EXACTLY);
        measureChildren(widthMeasureSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0, j = 0; i < getChildCount(); i++) {
                View v = getChildAt(i);
                if (v.getVisibility() == View.VISIBLE) {
                    v.layout(l, t * j * getMeasuredHeight(), r, b * (j + 1) * getMeasuredHeight());
                }
                j++;
            }
        }
    }


}
