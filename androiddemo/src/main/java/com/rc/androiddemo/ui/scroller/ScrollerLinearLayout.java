package com.rc.androiddemo.ui.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-24 17:22
 */
public class ScrollerLinearLayout  extends LinearLayout{

    private Scroller mScroller;

    private Button btn;

    public ScrollerLinearLayout(Context context) {
        this(context, null);
    }

    public ScrollerLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        btn = new Button(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        btn.setLayoutParams(lp);
        addView(btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                log();
                log2();
//                btn.offsetTopAndBottom(200);
                mScroller.startScroll(0, 200, 0, 100, 3000);
                invalidate();
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {
            log();
            log2();
        }
    }

    private void log() {
        Log.v(ScrollerLinearLayout.class.getSimpleName(), "-----left: " + btn.getLeft() +
                " ----right: " + btn.getRight() + " ------top: " + btn.getTop() +
                " ------bottom: " + btn.getBottom());
    }

    private void log2() {
        Log.v(ScrollerLinearLayout.class.getSimpleName(), "-----x: " + btn.getX() +
                " ----y: " + btn.getY() + " ------curx: " + mScroller.getCurrX() +
                " ------cury: " + mScroller.getCurrY());
    }
}

