package com.rc.androiddemo.ui.scroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-24 17:14
 */
public class ScrollerDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
    }

    public void click(View view) {
        TextView tv = (TextView) findViewById(R.id.tvTest);
        ScrollerLinearLayout sll = (ScrollerLinearLayout) findViewById(R.id.sll);
        tv.setText(sll.getLeft() + ", " + sll.getTop() + ", " + sll.getRight() + ", " + sll.getBottom() + sll.getScrollY());
    }
}
