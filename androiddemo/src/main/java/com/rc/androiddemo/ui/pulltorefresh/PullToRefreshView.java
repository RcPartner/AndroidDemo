package com.rc.androiddemo.ui.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-23 14:30
 */
public class PullToRefreshView extends ViewGroup {

    private int headerViewId;

    private View headerView;

    private int contentViewId;

    private View contentView;

    private int mHeaderHeight;

    private int offsetY;

    private int totalOffsetY;

    private int lastYPos;

    private Scroller mScroller;

    public PullToRefreshView(Context context) {
        this(context, null);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullToRefreshView, 0, 0);
        if (a != null) {
            headerViewId = a.getResourceId(R.styleable.PullToRefreshView_header_id, headerViewId);
            contentViewId = a.getResourceId(R.styleable.PullToRefreshView_content_id, contentViewId);
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (headerViewId != 0) {
            headerView = findViewById(headerViewId);
            MarginLayoutParams mp = new MarginLayoutParams(LayoutParams.MATCH_PARENT, 100);
            headerView.setLayoutParams(mp);
            headerView.offsetTopAndBottom(headerView.getMeasuredHeight());
        }

        if (contentViewId != 0) {
            contentView = findViewById(contentViewId);
            MarginLayoutParams mp = new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(mp);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//        headerView.measure(widthMeasureSpec, heightMeasureSpec);
        MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
        mHeaderHeight = lp.topMargin + lp.bottomMargin + headerView.getMeasuredHeight();
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        if (headerView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + headerView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + totalOffsetY - mHeaderHeight;
            int bottom = top + headerView.getMeasuredHeight();
            headerView.layout(left, top, right, bottom);
            log(headerView);
        }

        if (contentView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + contentView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + totalOffsetY;
            int bottom = top + contentView.getMeasuredHeight();
            contentView.layout(left, top, right, bottom);
            log(contentView);
        }

    }

    private void log(View v) {
        Log.v(v.getClass().getSimpleName(), "-----left: " + v.getLeft() +
                " ----right: " + v.getRight() + " ------top: " + v.getTop() +
                " ------bottom: " + v.getBottom());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                release();
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                offsetY = (int) (ev.getY() - lastYPos);
                totalOffsetY += offsetY;
                lastYPos = (int) ev.getY();
                updatePos(offsetY);
                return dispatchTouchEventSupper(ev);
            case MotionEvent.ACTION_DOWN:
                lastYPos = (int) ev.getY();
                dispatchTouchEventSupper(ev);
                return true;
        }
        return dispatchTouchEventSupper(ev);
    }

    private void updatePos(int offsetY) {
        headerView.offsetTopAndBottom(offsetY);
        contentView.offsetTopAndBottom(offsetY);
        invalidate();
        if (mHeaderHeight < totalOffsetY) {
            release();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
        if (mScroller.getCurrY() == totalOffsetY) {
            Log.v(this.getClass().getSimpleName(), "-------total offset is : " + totalOffsetY);
            totalOffsetY = 0;
        }
    }

    private boolean dispatchTouchEventSupper(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return ev.getAction() == MotionEvent.ACTION_DOWN && headerView.getY() > 0 ||
                super.onInterceptTouchEvent(ev);
    }

    private void release() {
        mScroller.startScroll(0, 0, 0, totalOffsetY, 1500);
        invalidate();
    }
}
