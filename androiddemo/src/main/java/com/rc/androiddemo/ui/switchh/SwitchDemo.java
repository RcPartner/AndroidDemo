package com.rc.androiddemo.ui.switchh;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-06-23 14:59
 */
public class SwitchDemo extends Activity {

    private Switch sDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_switch_demo);
        sDemo = (Switch) findViewById(R.id.sDemo);

//        sDemo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
    }

}
