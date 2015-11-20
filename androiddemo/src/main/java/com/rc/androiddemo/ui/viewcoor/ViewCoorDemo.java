package com.rc.androiddemo.ui.viewcoor;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-11-20 17:22
 */
public class ViewCoorDemo extends Activity {

    private TextView tvGetTop;
    private TextView tvGetBottom;
    private TextView tvGetY;
    private TextView tvGetLocationOnScreen;
    private TextView tvGetLocationInWindow;
    private TextView tvGetGlobalVisibleRect;
    private TextView tvGetLocalVisibleRect;
    private TextView tvTotal;
    private ScrollView sv;
    private View v1;
    private View v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coor);

        tvGetTop = (TextView) findViewById(R.id.tvGetTop);
        tvGetBottom = (TextView) findViewById(R.id.tvGetBottom);
        tvGetY = (TextView) findViewById(R.id.tvGetY);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvGetLocationOnScreen = (TextView) findViewById(R.id.tvGetLocationOnScreen);
        tvGetLocationInWindow = (TextView) findViewById(R.id.tvGetLocationInWindow);
        tvGetGlobalVisibleRect = (TextView) findViewById(R.id.tvGetGlobalVisibleRect);
        tvGetLocalVisibleRect = (TextView) findViewById(R.id.tvGetLocalVisibleRect);
        sv = (ScrollView) findViewById(R.id.sv);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);

        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tvTotal.setText("Total:" + sv.getMeasuredHeight());
                tvGetTop.setText("getTop:" + v1.getTop());
                tvGetBottom.setText("getBottom:" + v1.getBottom());
                tvGetY.setText("getY:" + v1.getY());
                int[] location = new int[2];
                int[] window = new int[2];
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                v1.getLocationOnScreen(location);
                tvGetLocationOnScreen.setText("getLocationScreen: X" + location[0] + ",Y:" + location[1]);
                v1.getLocationInWindow(window);
                tvGetLocationInWindow.setText("getWindow: X" + window[0] + ",Y:" + window[1]);
                v1.getGlobalVisibleRect(rect);
                tvGetGlobalVisibleRect.setText("GetGlobalVisibleRect: Top" + rect.top + ",Y:" + rect.bottom);
                v1.getLocalVisibleRect(rect2);
                tvGetLocalVisibleRect.setText("GetLocalVisibleRect: Top" + rect2.top + ",Y:" + rect2.bottom);
                return false;
            }
        });
    }
}
