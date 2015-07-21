package com.rc.androiddemo.ui.textview;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2014-12-24 10:20
 */
public class TextViewDemo extends Activity {

    private TextView tvDemo1;
    private TextView tvDemo2;
    private TextView tvDemo3;
    private TextView tvDemo4;
    private TextView tvDemo7;
    private TextView tvDemo8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_textview_demo);
        super.onCreate(savedInstanceState);
        initView();
        useTypeface();
        showHtmlCode();
        setTextColorSelector();
        setUnderLinerByHtml();
        setUnderLinerByJava();
    }

    protected void initView() {
        tvDemo1 = (TextView) this.findViewById(R.id.tvDemo1);
        tvDemo2 = (TextView) this.findViewById(R.id.tvDemo2);
        tvDemo3 = (TextView) this.findViewById(R.id.tvDemo3);
        tvDemo4 = (TextView) this.findViewById(R.id.tvDemo4);
        tvDemo7 = (TextView) this.findViewById(R.id.tvDemo7);
        tvDemo8 = (TextView) this.findViewById(R.id.tvDemo8);
    }

    /**
     * 使用其他字体
     */
    private void useTypeface() {
        Typeface tyTemp = Typeface.createFromAsset(getAssets(), "fonts/fzzyjt.ttf");
        tvDemo2.setTypeface(tyTemp);
        tvDemo2.setText("我使用了第三方字体");
    }

    /**
     * 解析html代码
     */
    private void showHtmlCode() {
        tvDemo3.setText(Html.fromHtml("<font color=\"#FF0000\">我是html代码</font>"));
    }

    /**
     * 为文本的颜色设置selector
     */
    private void setTextColorSelector() {
        ColorStateList cslTemp = getResources().getColorStateList(R.color.selector_text_color);
        tvDemo4.setText("java代码设置字体颜色selector：按下我由黑色变红色");
        tvDemo4.setOnClickListener(null);//要设置字体颜色selector，必须让控件可点击
    }

    /**
     * 通过html代码的方式设置下划线
     */
    private void setUnderLinerByHtml() {
        tvDemo7.setText(Html.fromHtml("<u>通过html代码的方式设置下划线</u>"));
    }

    /**
     * 通过java代码的方式设置下划线
     */
    private void setUnderLinerByJava() {
        tvDemo8.setText("通过java代码的方式设置下划线");
        tvDemo8.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
    /**
     //     * 通过java代码的方式设置中划线
     //     */
//    private void setUnderLinerByJava() {
//        tvDemo8.setText("通过java代码的方式设置下划线");
//        tvDemo8.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        viewHolder[i].tvGoodsPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//    }

}
