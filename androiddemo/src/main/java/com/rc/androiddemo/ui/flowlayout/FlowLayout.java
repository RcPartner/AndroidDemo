package com.rc.androiddemo.ui.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.rc.androiddemo.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-24 15:31
 */
public class FlowLayout extends ViewGroup implements BaseTagAdapter.Observer {

    private int maxSelectCount;

    private boolean isSupportSelect;

    private HashSet<Integer> checkedTagList;

    private BaseTagAdapter mAdapter;

    private MotionEvent motionEvent;

    private OnTagSelectListener onTagSelectListener;

    private OnTagClickListener onTagClickListener;

    public FlowLayout(Context context, int maxSelectCount, boolean isSupportSelect) {
        super(context);
        setMaxSelectCount(maxSelectCount);
        setIsSupportSelect(isSupportSelect);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        maxSelectCount = a.getInt(R.styleable.FlowLayout_multi_select_count, -1);
        isSupportSelect = a.getBoolean(R.styleable.FlowLayout_multi_select, false);
        a.recycle();

        if (isSupportSelect()) {
            checkedTagList = new HashSet<>();
        }
    }

    public int getMaxSelectCount() {
        return maxSelectCount;
    }

    public void setMaxSelectCount(int maxSelectCount) {
        this.maxSelectCount = maxSelectCount;
    }

    public boolean isSupportSelect() {
        return isSupportSelect;
    }

    public void setIsSupportSelect(boolean isSupportSelect) {
        this.isSupportSelect = isSupportSelect;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();

        int maxWidth = 0;
        int columnWidth = 0;
        int rowHeight = 0;
        int maxHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            if (columnWidth + childWidth > widthSize) {
                maxWidth = Math.max(columnWidth, maxWidth);
                rowHeight += maxHeight;
            } else {
                columnWidth += childWidth;
                maxHeight = Math.max(maxHeight, childHeight);
            }
        }
        rowHeight += maxHeight;

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize :
                maxWidth, heightMode == MeasureSpec.EXACTLY ? heightSize : rowHeight);
    }

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {

        int width = getWidth();

        int currentWidth = 0;
        int currentHeight = 0;
        int totalHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (currentWidth + lp.leftMargin + childWidth + lp.rightMargin <= width) {
                currentWidth =  currentWidth + lp.leftMargin + childWidth + lp.rightMargin;
                currentHeight = Math.max(currentHeight, childHeight + lp.topMargin + lp.bottomMargin);
            } else {
                totalHeight += currentHeight;
                currentHeight = 0;
                currentWidth = 0;
            }
            child.layout(currentWidth - lp.topMargin - childWidth, totalHeight + lp.topMargin,
                    currentWidth - lp.rightMargin, totalHeight + lp.topMargin + childHeight);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            motionEvent = MotionEvent.obtain(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        if (motionEvent == null) {
            return super.performClick();
        }
        int pos = findClickChild((int) motionEvent.getX(), (int) motionEvent.getY());
        motionEvent = null;
        if (pos != -1) {
            TagViewGroup tagView = (TagViewGroup) getChildAt(pos);
            if (isSupportSelect()) {
                handleSelect(pos, tagView);
                if (onTagSelectListener != null) {
                    onTagSelectListener.onSelected(new HashSet<>(checkedTagList), tagView.getTagView());
                }
            } else {
                if (onTagClickListener != null) {
                    return onTagClickListener.onClick(pos, tagView.getTagView());
                }
            }
        }

        return super.performClick();
    }

    private int findClickChild(int x, int y) {

        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v.getVisibility() != VISIBLE) {
                continue;
            }
            Rect rect = new Rect();
            v.getHitRect(rect);
            if (rect.contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    private void handleSelect(int pos, TagViewGroup v) {
        v.setChecked(!v.isChecked());
        if (!v.isChecked()) {
            checkedTagList.remove(pos);
        }else if (maxSelectCount == -1 || checkedTagList.size() <= maxSelectCount){
            checkedTagList.add(pos);
        }
    }

    public BaseTagAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseTagAdapter mAdapter) {
        this.mAdapter = mAdapter;
        mAdapter.registerObserver(this);
        refreshAdapter();
    }

    private void refreshAdapter() {
        removeAllViews();
        if (checkedTagList != null) {
            checkedTagList.clear();
        }
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View v = mAdapter.getView(this, i, mAdapter.getItem(i));
            v.setDuplicateParentStateEnabled(true);
            TagViewGroup tvg = new TagViewGroup(getContext());
            tvg.setLayoutParams(v.getLayoutParams());
            tvg.addView(v);
            addView(tvg);
        }
    }

    @Override
    public void updateView() {
        refreshAdapter();
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.onTagSelectListener = onTagSelectListener;
        if (this.onTagSelectListener != null) {
            setClickable(true);
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
        if (this.onTagClickListener != null) {
            setClickable(true);
        }
    }

    public interface OnTagSelectListener {
        void onSelected(Set<Integer> selectSet, View view);
    }

    public interface OnTagClickListener {
        boolean onClick(int position, View view);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
