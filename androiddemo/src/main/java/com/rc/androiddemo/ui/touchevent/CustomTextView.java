package com.rc.androiddemo.ui.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-29 18:06
 */
public class CustomTextView extends TextView {

    private IUpdate iUpdate;

    private boolean isEvent;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (iUpdate != null) {
                    iUpdate.update("CustomTextView:onTouchEvent ACTION_DOWN ---");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (iUpdate != null) {
                    iUpdate.update("CustomTextView:onTouchEvent ACTION_MOVE ---");
                }
                break;
            case MotionEvent.ACTION_UP:
                if (iUpdate != null) {
                    iUpdate.update("CustomTextView:onTouchEvent ACTION_UP ---");
                }
                break;
        }
        return isEvent;
    }

    public void setiUpdate(IUpdate iUpdate) {
        this.iUpdate = iUpdate;
    }

    public void setIsEvent(boolean isEvent) {
        this.isEvent = isEvent;
    }
}
