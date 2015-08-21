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

    /**
     * 头部ViewID
     */
    private int headerViewId;
    /**
     *
     */
    private View headerView;

    private int contentViewId;

    private View contentView;

    private int footerViewId;

    private View footerView;

    private int mHeaderHeight;

    private boolean isRelease = false;

    private PtrManager ptrManager;

    private IPullUpOrDownController iPullUpOrDownController;

    private Scroller mScroller;

    public PullToRefreshView(Context context) {
        this(context, null);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ptrManager = new PtrManager();
        mScroller  = new Scroller(context);
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
        if (getChildCount() > 3) {
            throw new IllegalArgumentException("child view can't more than three!");
        }
        if (headerViewId != 0) {
            headerView = findViewById(headerViewId);
            PtrvMarginLayoutParams mp = new PtrvMarginLayoutParams(
                    PtrvMarginLayoutParams.MATCH_PARENT, 100);
            headerView.setLayoutParams(mp);
//            headerView.offsetTopAndBottom(headerView.getMeasuredHeight());
        }

        if (contentViewId != 0) {
            contentView = findViewById(contentViewId);
            PtrvMarginLayoutParams mp = new PtrvMarginLayoutParams(
                    PtrvMarginLayoutParams.MATCH_PARENT, PtrvMarginLayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(mp);
        }
    }

    private void initChildView(int id, View view) {
        view = findViewById(id);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//        headerView.measure(widthMeasureSpec, heightMeasureSpec);
        PtrvMarginLayoutParams lp = (PtrvMarginLayoutParams) headerView.getLayoutParams();
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
            int top = paddingTop + lp.topMargin + ptrManager.totalOffsetY - mHeaderHeight;
            int bottom = top + headerView.getMeasuredHeight();
            headerView.layout(left, top, right, bottom);
            log(headerView);
        }

        if (contentView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + contentView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + ptrManager.totalOffsetY;
            int bottom = top + contentView.getMeasuredHeight();
            contentView.layout(left, top, right, bottom);
            log(contentView);
            Log.v(this.getClass().getSimpleName(), "-------contentView : " + contentView.getMeasuredHeight() + ",  " + getBottom());
        }

    }

    private void log(View v) {
        Log.v(v.getClass().getSimpleName(), "-----left: " + v.getLeft() +
                " ----right: " + v.getRight() + " ------top: " + v.getTop() +
                " ------bottom: " + v.getBottom());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean bool = dispatchTouchEventSupper(ev);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ptrManager.isPulling = false;
                release();
//                return super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int offset = ptrManager.moveOffset(ev.getY());
                if ((ptrManager.isMoveUp && (iPullUpOrDownController.canPullUp(this, contentView) ||
                        getScrollY() < 0) || (ptrManager.isMoveDown && iPullUpOrDownController
                        .canPullDown(this, contentView)))) {
                    updatePos(offset);
                }
//                return dispatchTouchEventSupper(ev);
                break;
            case MotionEvent.ACTION_DOWN:
                ptrManager.lastYPos = (int) ev.getY();
//                dispatchTouchEventSupper(ev);
                break;
        }
        return bool;
    }

    private void updatePos(int offsetY) {
        //亦可通过以下两个方法实现视图跟随手指滑动偏移效果
//        headerView.offsetTopAndBottom(offsetY);
//        contentView.offsetTopAndBottom(offsetY);
        scrollBy(0, -offsetY);
        invalidate();
//        lastTotalOffsetY -= offsetY;
//        if (mHeaderHeight < totalOffsetY) {
//            release();
//        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }else if (mScroller.isFinished()) {
            if (isRelease) {
                ptrManager.totalOffsetY = 0;
                Log.v(this.getClass().getSimpleName(), "-------current header height is : " + (headerView.getBottom() - getScrollY()));
                Log.v(this.getClass().getSimpleName(), "-------current scroll offset is : " + getY());
                isRelease = false;
                log(headerView);
                log(contentView);
            }

        }
//        if (mScroller.getCurrY() == totalOffsetY) {
//            Log.v(this.getClass().getSimpleName(), "-------total offset is : " + totalOffsetY);
//            totalOffsetY = 0;
//        }

    }

    private boolean dispatchTouchEventSupper(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean intercept = false;
        if (iPullUpOrDownController == null) {
            iPullUpOrDownController = new PullUpOrDownController();
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                intercept = (ptrManager.isMoveUp && iPullUpOrDownController.canPullUp(this, contentView)) ||
                        (ptrManager.isMoveDown && iPullUpOrDownController.canPullDown(this, contentView));
                break;
            case MotionEvent.ACTION_DOWN:
                intercept =  ptrManager.isPulling;
                break;
        }
        return intercept;
    }

    private void release() {
        if (isRelease) {
            return;
        }
        isRelease = true;
        mScroller.startScroll(0, ptrManager.totalOffsetY, 0, -(ptrManager.totalOffsetY), 1500);
        Log.v(this.getClass().getSimpleName(), "-------start scroll offset is : " + ptrManager.totalOffsetY);
        Log.v(this.getClass().getSimpleName(), "-------final scroll offset is : " + -ptrManager.totalOffsetY);
        invalidate();
    }

    public void setiPullUpOrDownController(IPullUpOrDownController iPullUpOrDownController) {
        this.iPullUpOrDownController = iPullUpOrDownController;
    }

    public class PtrvMarginLayoutParams extends MarginLayoutParams {
        public PtrvMarginLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public PtrvMarginLayoutParams(int width, int height) {
            super(width, height);
        }

        public PtrvMarginLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public PtrvMarginLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
