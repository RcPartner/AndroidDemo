package com.rc.androiddemo.ui.animation;


import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rc.androiddemo.R;

/**
 * Description:
 * User: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2014-12-09 10:36
 */
public class TweenedAnimation extends Activity {

    private ImageView ivRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened_animation);
        ivRotate = (ImageView) findViewById(R.id.ivRotate);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        ivRotate.startAnimation(animation);
    }
}
