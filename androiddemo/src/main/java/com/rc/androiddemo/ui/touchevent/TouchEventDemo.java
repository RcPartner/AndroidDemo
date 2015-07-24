package com.rc.androiddemo.ui.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.rc.androiddemo.R;


/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-29 14:32
 */
public class TouchEventDemo extends Activity implements IUpdate{

    private StringBuilder stringBuilder;

    private TextView tvContent;

    @Override
    public void update(String content) {
        stringBuilder.append(content + "\n");
        tvContent.setText(stringBuilder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        final CustomLinearLayout view1 = (CustomLinearLayout) findViewById(R.id.cllView1);
        final CustomLinearLayout view2 = (CustomLinearLayout) findViewById(R.id.cllView2);
        final CustomTextView ctvView3  = (CustomTextView) findViewById(R.id.ctvView3);
        Switch sIntercept       = (Switch) findViewById(R.id.sIntercept);
        Switch sIntercept2      = (Switch) findViewById(R.id.sIntercept2);
        Switch sEvent           = (Switch) findViewById(R.id.sEvent);
        Switch sEvent2          = (Switch) findViewById(R.id.sEvent2);
        Switch sEvent3          = (Switch) findViewById(R.id.sEvent3);
        tvContent                      = (TextView) findViewById(R.id.tvContent);
        stringBuilder                  = new StringBuilder();

        view1.setTAG("view1");
        view2.setTAG("view2");
        ctvView3.setiUpdate(this);
        view1.setiUpdate(this);
        view2.setiUpdate(this);
        sIntercept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                view1.setIsIntercept(isChecked);
            }
        });

        sIntercept2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                view2.setIsIntercept(isChecked);
            }
        });

        sEvent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                view1.setIsConsumeEvent(isChecked);
            }
        });

        sEvent2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                view2.setIsConsumeEvent(isChecked);
            }
        });

        sEvent3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clear();
                ctvView3.setIsEvent(isChecked);
            }
        });
    }

    private void clear() {
        stringBuilder.delete(0, stringBuilder.length());
        tvContent.setText(stringBuilder);
    }
}
