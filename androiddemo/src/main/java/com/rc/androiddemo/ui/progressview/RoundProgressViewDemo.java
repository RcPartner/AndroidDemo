package com.rc.androiddemo.ui.progressview;

import android.app.Activity;
import android.os.Bundle;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-16 16:27
 */
public class RoundProgressViewDemo extends Activity {

    private RoundProgressView rpvTest;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_progress_view_demo);

        rpvTest = (RoundProgressView) findViewById(R.id.rpvTest);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    rpvTest.setProgress(count++ % 100);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
