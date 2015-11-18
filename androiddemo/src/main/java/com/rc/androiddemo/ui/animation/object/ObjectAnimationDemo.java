package com.rc.androiddemo.ui.animation.object;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-11-16 11:52
 */
public class ObjectAnimationDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);

        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ValueAnimator animator = ValueAnimator.ofInt(240, 1000);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int width = (int) animation.getAnimatedValue();
                        v.getLayoutParams().width = width;
                        v.requestLayout();
                    }
                });
                animator.start();
            }
        });
    }
}
