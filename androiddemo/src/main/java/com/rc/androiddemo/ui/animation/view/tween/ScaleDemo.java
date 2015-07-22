package com.rc.androiddemo.ui.animation.view.tween;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-03-24 20:02
 */
public class ScaleDemo extends Activity {

    private TextView tvDemo;
    private TextView tvStart;
    private TextView tvStart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_demo);
        tvDemo = (TextView) findViewById(R.id.tvDemo);
        tvStart = (TextView) findViewById(R.id.tvStart);
        tvStart1 = (TextView) findViewById(R.id.tvStart1);

        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这个动画中你会发现控件显示被直接重绘到你指定的动画开始的x、y坐标，
                // 然后再开始播放动画效果，所以一般动画的开始x、y坐标应该是控件原本的x、y坐标
                Animation animation = AnimationUtils.loadAnimation(ScaleDemo.this, R.anim.scale);
                tvDemo.startAnimation(animation);
            }
        });

        tvStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 0.5f, 0.5f, 0.5f);
                animation.setDuration(2000);
                tvDemo.startAnimation(animation);
            }
        });
    }
}
