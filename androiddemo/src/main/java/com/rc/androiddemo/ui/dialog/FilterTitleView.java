package com.rc.androiddemo.ui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-22 09:40
 */
public class FilterTitleView extends LinearLayout {

    private int itemLength;

    private int dividerColor;

    private int dividerWidth;

    private final int DEFAULT_WIDTH = 6;

    private String[] strItems;

    private IGetItemView iGetItemView;

    public FilterTitleView(Context context) {
        this(context, null);
    }

    public FilterTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilterTitleView);
        int textIds = a.getResourceId(R.styleable.FilterTitleView_items, 0);
        if (textIds == 0) {
            throw new IllegalArgumentException("items property must be assigned!");
        }
        strItems = a.getResources().getStringArray(textIds);
        itemLength = strItems.length;
        dividerWidth = a.getDimension(R.styleable.FilterTitleView_dividerWidth, DEFAULT_WIDTH);
        dividerColor = a.getColor(R.styleable.FilterTitleView_dividerColor, Color.GRAY);

        a.recycle();

        setItemsView();
    }

    private void setItemsView() {
        if (strItems == null || itemLength == 0) {
            return;
        }
        for (int i = 0; i < itemLength; i++) {
            View v = iGetItemView == null ? getDefaultItemView(i) : iGetItemView.getView(i);
            if (v instanceof TextView) {
                ((TextView) v).setText(strItems[i]);
            }
            addView(v);
            if (i != itemLength - 1) {
                addDivider();
            }
        }
    }

    private TextView getDefaultItemView(int position) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        tv.setGravity(Gravity.CENTER);
        int padding = getResources().getDimension(R.dimen.default_padding);
        tv.setPadding(padding, padding, padding, padding);
        tv.setLayoutParams(lp);
        return tv;
    }

    private void addDivider() {
        View v = new View(getContext());
        LayoutParams lp = new LayoutParams(dividerWidth, LayoutParams.MATCH_PARENT);
        int margin = getResources().getDimension(R.dimen.default_divider_padding);
        lp.setMargins(0, margin, 0, margin);
        v.setLayoutParams(lp);
        v.setBackgroundColor(dividerColor);
        addView(v);
    }

    public interface IGetItemView {
        View getView(int position);
    }
}
