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

//    private int mHeaderHeight;

    private boolean isRelease = false;

    private PtrManager ptrManager;

    private IPullUpOrDownController iPullUpOrDownController;

    private OnRefreshListener onRefreshListener;

    private OnLoadMoreListener onLoadMoreListener;

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
            footerViewId = a.getResourceId(R.styleable.PullToRefreshView_footer_id, footerViewId);
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 3) {
            throw new IllegalArgumentException("child view can't more than three!");
        }
        if (getChildCount() < 1) {
            throw new IllegalArgumentException("must have one content view!");
        }
        if (headerViewId != 0) {
            headerView = findViewById(headerViewId);
//            headerView.offsetTopAndBottom(headerView.getMeasuredHeight());
        }

        if (contentViewId != 0) {
            contentView = findViewById(contentViewId);
        }

        if (contentView == null) {
            contentView = getChildAt(0);
        }

        if (footerViewId != 0) {
            footerView = findViewById(footerViewId);
        }
    }

    public void setHeaderView(ICustomRefreshView iCustomRefreshView) {
        if (iCustomRefreshView != null) {
            headerView = iCustomRefreshView.getView();
            addView(headerView);
        }
    }

    public void setFooterView(ICustomRefreshView iCustomRefreshView) {
        if (iCustomRefreshView != null) {
            footerView = iCustomRefreshView.getView();
            addView(footerView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (headerView != null) {
            measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
            ptrManager.mHeaderHeight = lp.topMargin + lp.bottomMargin + headerView.getMeasuredHeight();
        }

        contentView.measure(widthMeasureSpec, heightMeasureSpec);

        if (footerView != null) {
            measureChildWithMargins(footerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp2 = (MarginLayoutParams) footerView.getLayoutParams();
            ptrManager.mFooterHeight = lp2.topMargin + lp2.bottomMargin + footerView.getMeasuredHeight();
        }
//        headerView.measure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (isRelease) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        if (headerView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + headerView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + ptrManager.totalOffsetYAbs - ptrManager.mHeaderHeight;
            int bottom = top + headerView.getMeasuredHeight();
            headerView.layout(left, top, right, bottom);
            log(headerView);
        }

        if (contentView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + contentView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + ptrManager.totalOffsetYAbs;
            int bottom = top + contentView.getMeasuredHeight();
            contentView.layout(left, top, right, bottom);
            log(contentView);
            Log.v(this.getClass().getSimpleName(), "-------contentView : " + contentView.getMeasuredHeight() + ",  " + getBottom());
        }

        if (footerView != null && contentView != null) {
            MarginLayoutParams lp = (MarginLayoutParams) footerView.getLayoutParams();
            int left = paddingLeft + lp.leftMargin;
            int right = left + footerView.getMeasuredWidth();
            int top = paddingTop + lp.topMargin + ptrManager.totalOffsetYAbs + contentView.getBottom();
            int bottom = top + ptrManager.mFooterHeight;
            footerView.layout(left, top, right, bottom);
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
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (ptrManager.isPulling) {
                    ptrManager.isPulling = false;
                    int offset = ptrManager.isMoveDown ? ptrManager.totalOffsetY + ptrManager.
                            mHeaderHeight : ptrManager.totalOffsetY - ptrManager.mFooterHeight;
                    release(ptrManager.totalOffsetY, offset);
                    if (ptrManager.isMoveDown && ptrManager.totalOffsetYAbs > ptrManager.mHeaderHeight
                            && onRefreshListener != null) {
                        onRefreshListener.onRefresh();

                        if (headerView instanceof IPullToRefreshCallBack) {
                            ((IPullToRefreshCallBack) headerView).refreshing();
                        }
                    }
                    if (!ptrManager.isMoveDown && ptrManager.totalOffsetYAbs >
                            ptrManager.mFooterHeight && onLoadMoreListener != null) {
                        footerView.setVisibility(VISIBLE);
                        onLoadMoreListener.onLoadMore();

                        if (footerView instanceof IPullToRefreshCallBack) {
                            ((IPullToRefreshCallBack) footerView).refreshing();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                ptrManager.judgePullUpOrDown(ev.getY());
                if ((!ptrManager.isMoveDown && (iPullUpOrDownController.canPullUp(this, contentView) ||
                        getScrollY() < 0) || (ptrManager.isMoveDown && iPullUpOrDownController
                        .canPullDown(this, contentView)))) {
                    int offset = ptrManager.moveOffset(ev.getY());
                    updatePos(offset);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                ptrManager.lastYPos = (int) ev.getY();
                break;
        }
        boolean bool = dispatchTouchEventSupper(ev);
        return bool;
    }

    private void updatePos(int offsetY) {
        //亦可通过以下两个方法实现视图跟随手指滑动偏移效果
//        headerView.offsetTopAndBottom(offsetY);
//        contentView.offsetTopAndBottom(offsetY);
        scrollBy(0, -offsetY);
        invalidate();
        if (ptrManager.isMoveDown && headerView instanceof IPullToRefreshCallBack) {
            ((IPullToRefreshCallBack) headerView).pullOffsetPercent((float) ptrManager.totalOffsetYAbs
                    / (ptrManager.mHeaderHeight * 100));
        }
        if (!ptrManager.isMoveDown && footerView instanceof IPullToRefreshCallBack) {
            ((IPullToRefreshCallBack) footerView).pullOffsetPercent((float) ptrManager.totalOffsetYAbs
                    / (ptrManager.mFooterHeight * 100));
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }else {
            if (isRelease) {
                ptrManager.totalOffsetY = 0;
                ptrManager.totalOffsetYAbs = 0;
                Log.v(this.getClass().getSimpleName(), "-------current header height is : " + (headerView.getBottom() - getScrollY()));
                Log.v(this.getClass().getSimpleName(), "-------current scroll offset is : " + getY());
                isRelease = false;
                log(headerView);
                log(contentView);
            }

        }
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
                intercept = (!ptrManager.isMoveDown && iPullUpOrDownController.canPullUp(this, contentView)) ||
                        (ptrManager.isMoveDown && iPullUpOrDownController.canPullDown(this, contentView));
                break;
            case MotionEvent.ACTION_DOWN:
                intercept =  ptrManager.isPulling;
                break;
        }
        return intercept;
    }

    private void release(int startY, int dy) {
        if (isRelease) {
            return;
        }
        isRelease = true;
        mScroller.startScroll(0, startY, 0, -dy, ptrManager.releaseDuration);
        Log.v(this.getClass().getSimpleName(), "-------start scroll offset is : " + ptrManager.totalOffsetY);
        Log.v(this.getClass().getSimpleName(), "-------final scroll offset is : " + -ptrManager.totalOffsetY);
        invalidate();
    }

    public void refreshComplete() {
        release(ptrManager.mHeaderHeight, ptrManager.mHeaderHeight);
        if (headerView instanceof IPullToRefreshCallBack) {
            ((IPullToRefreshCallBack) headerView).refreshCompleted();
        }
    }

    public void loadMoreComplete() {
//        release(ptrManager.mFooterHeight, ptrManager.mFooterHeight);
        footerView.setVisibility(GONE);
        if (footerView instanceof IPullToRefreshCallBack) {
            ((IPullToRefreshCallBack) footerView).refreshCompleted();
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setiPullUpOrDownController(IPullUpOrDownController iPullUpOrDownController) {
        this.iPullUpOrDownController = iPullUpOrDownController;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
