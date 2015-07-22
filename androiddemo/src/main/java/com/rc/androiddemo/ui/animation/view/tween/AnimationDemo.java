package com.rc.androiddemo.ui.animation.view.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.rc.androiddemo.R;


/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-26
 * Time: 10:59
 * Description：
 */
public class AnimationDemo extends Activity {

//    private Animation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    public void alphaXml(View v) {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        v.startAnimation(alphaAnimation);
    }

    public void rotateXml(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        v.startAnimation(animation);
    }

    public void scaleXml(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        v.startAnimation(animation);
    }

    public void translateXml(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        v.startAnimation(animation);
    }

    public void alphaJava(View v) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(3000);
        v.startAnimation(alphaAnimation);
    }

    public void rotateJava(View v) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, -350, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(3000);
        v.startAnimation(rotateAnimation);
    }

    public void scaleJava(View v) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(3000);
        v.startAnimation(scaleAnimation);
    }

    public void translateJava(View v) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f,  50f, 0.0f, 50f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(3000);
        v.startAnimation(translateAnimation);
    }

    public void customAnimation(View v) {
        AnimationSet animationSet = new AnimationSet(false);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        CustomAnimation customAnimation = new CustomAnimation();
        animationSet.addAnimation(animation);
        animationSet.addAnimation(customAnimation);
        v.clearAnimation();
        v.setAnimation(animationSet);
        animationSet.start();
    }

    public void rotate3DAnimation(View v) {
        Rotate3DAnimation rotate3DAnimation = new Rotate3DAnimation();
        v.startAnimation(rotate3DAnimation);
    }
}
