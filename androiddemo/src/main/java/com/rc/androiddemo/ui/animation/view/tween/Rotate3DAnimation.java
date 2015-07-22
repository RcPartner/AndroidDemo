package com.rc.androiddemo.ui.animation.view.tween;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-31
 * Time: 11:13
 * Description£º
 */
public class Rotate3DAnimation extends Animation {

    private int halfWidth;

    private int halfHeight;

    private Camera camera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        halfWidth = width / 2;
        halfHeight = height / 2;
        setDuration(3000);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    public boolean getTransformation(long currentTime, Transformation outTransformation) {
        return super.getTransformation(currentTime, outTransformation);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateX(360 * interpolatedTime);
        camera.getMatrix(matrix);
        matrix.preTranslate(0.0f, -halfHeight);
        matrix.postTranslate(0.0f, halfHeight);
        camera.restore();
    }
}
