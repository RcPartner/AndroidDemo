package com.rc.androiddemo.ui.animation.view.tween;

import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Transformation;

/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-27
 * Time: 15:08
 * Description：
 */
public class CustomAnimation extends Animation {

    int halfWidth;

    int halfHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

//        setFillAfter(true);
        setDuration(1000);
        halfWidth = width / 2;
        halfHeight = height / 2;
//        setRepeatCount(1);
        setInterpolator(new CycleInterpolator(1));
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
//        if (interpolatedTime <= 0.25) {
//            matrix.preTranslate(-interpolatedTime * 30, 0);
//        } else if (interpolatedTime > 0.25 && interpolatedTime <= 0.75) {
//            matrix.preTranslate(interpolatedTime * 60, 0);
//            matrix.postTranslate(-30, 0);
//        } else {
//            matrix.preTranslate(-interpolatedTime * 30, 0);
//            matrix.postTranslate(30, 0);
//        }
        matrix.preTranslate(-interpolatedTime * 30, interpolatedTime * 30);
//        matrix.postTranslate(30, 0);
        if (hasEnded()) {
            Log.v("ShakeAnimation", "Time is : -------------" + interpolatedTime);
        }
//        matrix.preTranslate(interpolatedTime, interpolatedTime);
//        matrix.preRotate(interpolatedTime * 360);
//        matrix.preTranslate(-halfWidth, -halfHeight);
//        matrix.postTranslate(halfWidth, halfHeight);
    }
}
