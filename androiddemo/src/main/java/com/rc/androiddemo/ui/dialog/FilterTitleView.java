package com.rc.androiddemo.ui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-22 09:40
 */
public class FilterTitleView extends LinearLayout {

    private int itemLength;

    private int dividerColor;

    private float dividerWidth;

    private final int DEFAULT_WIDTH = 6;

    private String[] strItems;

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
    }

    public interface IGetItemView {
        View getView(int position);
    }
}
