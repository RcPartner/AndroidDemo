package com.rc.androiddemo.ui.animation.view.tween;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-03-24 20:02
 */
public class TranslateDemo extends Activity {

    private TextView tvDemo;
    private TextView tvMove;
    private TextView tvMove1;

    private LinearLayout llDemo2;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvNew;
    private TextView tvOutOfRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_demo);
        tvDemo = (TextView) findViewById(R.id.tvDemo);
        tvMove = (TextView) findViewById(R.id.tvMove);
        tvMove1 = (TextView) findViewById(R.id.tvMove1);

        llDemo2 = (LinearLayout) findViewById(R.id.llDemo2);
        tvLeft = (TextView) findViewById(R.id.tvLeft);
        tvRight = (TextView) findViewById(R.id.tvRight);
        tvOutOfRight = (TextView) findViewById(R.id.tvOutOfRight);
        tvNew = (TextView) findViewById(R.id.tvNew);

        tvMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这个动画中你会发现控件显示被直接重绘到你指定的动画开始的x、y坐标，
                // 然后再开始播放动画效果，所以一般动画的开始x、y坐标应该是控件原本的x、y坐标
                Animation animation = AnimationUtils.loadAnimation(TranslateDemo.this, R.anim.translate);
                tvDemo.startAnimation(animation);
            }
        });

        tvMove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用java代码创建一个位移动画
                Animation animation = new TranslateAnimation(50, 600, 0, 0);
                animation.setDuration(2000);
                tvDemo.startAnimation(animation);
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final int width = dm.widthPixels;

        tvLeft.setLayoutParams(new LinearLayout.LayoutParams(width / 3 * 2, 100));
        tvRight.setLayoutParams(new LinearLayout.LayoutParams(width / 3, 100));
        tvNew.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));

        tvOutOfRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("xxxxxxxxxxxxxxxxx" +tvRight.getX());
                System.out.println("yyyyyyyyyyyyyyyyy" +tvRight.getY());
                System.out.println("llDemo2yyyyyyyyyy" +llDemo2.getY());
                System.out.println("widthwidth" +width);
               // Animation animation = new TranslateAnimation(tvRight.getX(), width, llDemo2.getY(), llDemo2.getY());
                //坐标是相对控件本身的
                Animation animation = new TranslateAnimation(0, 50, 0, 0);
                animation.setDuration(2000);
                tvRight.startAnimation(animation);

                Animation animation1 = new ScaleAnimation(1, 3, 1, 1,1,1);
                animation1.setDuration(2000);
                tvLeft.startAnimation(animation1);


                tvLeft.setVisibility(View.GONE);
                tvRight.setVisibility(View.GONE);
                tvNew.setVisibility(View.VISIBLE);
            }
        });

    }
}
