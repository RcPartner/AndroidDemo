package com.rc.androiddemo.ui.progressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-16 09:33
 */
public class RoundProgressView extends View {

    /**
     * 画笔引用
     */
    private Paint mPaint;

    /**
     * 圆环颜色
     */
    private int roundColor;

    /**
     * 进度条颜色
     */
    private int progressColor;

    /**
     * 最大进度值
     */
    private int maxProgress;

    /**
     * 进度条宽度
     */
    private float roundWidth;

    /**
     * 字体颜色
     */
    private int textColor;

    /**
     * 字体大小
     */
    private float textSize;

    /**
     * 当前样式
     */
    private int currentStyle;

    /**
     * 实心
     */
    private final int FILL = 0;

    /**
     * 空心
     */
    private final int STROKE = 1;

    /**
     * 中间是否显示文字提示
     */
    private boolean textDisplayable;

    /**
     * 当前进度
     */
    private int progress;

    private RectF rectF;

    public RoundProgressView(Context context) {
        this(context, null);
    }

    public RoundProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        rectF = new RectF();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressView);

        roundColor = mTypedArray.getColor(R.styleable.RoundProgressView_roundColor, Color.RED);
        progressColor = mTypedArray.getColor(R.styleable.RoundProgressView_roundProgressColor, Color.BLUE);
        maxProgress = mTypedArray.getInteger(R.styleable.RoundProgressView_maxProgress, 100);
        currentStyle = mTypedArray.getInt(R.styleable.RoundProgressView_style, 1);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressView_textSize, 15);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressView_textColor, Color.GREEN);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressView_roundWidth, 5);
        textDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressView_textDisplayable, true);

        mTypedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆环
        int center = getWidth() / 2;
        int radius = (int) (center - roundWidth);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(roundColor);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius, mPaint);

        //画百分比
        mPaint.setStrokeWidth(0);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = ( (int) (((float) progress / (float) maxProgress) * 100));
        float textWidth = mPaint.measureText(percent + "%");

        if (textDisplayable && currentStyle == STROKE) {
            canvas.drawText(percent + "%", center - textWidth / 2, center + textSize / 2, mPaint);
        }

        //画进度
        mPaint.setColor(progressColor);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setAntiAlias(true);

        rectF.set(center - radius, center - radius, center + radius, center + radius);

        switch (currentStyle) {
            case FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawArc(rectF, 0, 360 * progress / maxProgress, true, mPaint);
                break;
            case STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF, 0, 360 * progress / maxProgress, false, mPaint);
                break;
        }
    }

    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > maxProgress) {
            this.progress = maxProgress;
        } else {
            this.progress = progress;
            postInvalidate();
        }
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setMaxProgress(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("maxProgress must not less than 0");
        }
        this.maxProgress = max;
    }

    public synchronized int getMaxProgress() {
        return maxProgress;
    }

    public boolean isTextDisplayable() {
        return textDisplayable;
    }

    public void setTextDisplayable(boolean textDisplayable) {
        this.textDisplayable = textDisplayable;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getCurrentStyle() {
        return currentStyle;
    }

    public void setCurrentStyle(int currentStyle) {
        this.currentStyle = currentStyle;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }
}
