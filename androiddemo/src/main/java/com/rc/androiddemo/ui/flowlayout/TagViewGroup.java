package com.rc.androiddemo.ui.flowlayout;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-25 10:43
 */
public class TagViewGroup extends FrameLayout implements Checkable {

    private boolean isChecked;

    private static final int[] CHECK_STATE = new int[]{android.R.attr.state_checked};

    public TagViewGroup(Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean checked) {
        if (this.isChecked != checked)
        {
            this.isChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(states, CHECK_STATE);
        }
        return states;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    public View getTagView() {
        return getChildAt(0);
    }
}
