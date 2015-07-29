package com.rc.androiddemo.ui.textview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-29 11:04
 */
public class AutoCompleteTextViewDemo extends Activity {

    private AutoCompleteTextView actvDemo;
    private ArrayAdapter<String> actvDemoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_textview_demo);
        actvDemo = (AutoCompleteTextView) findViewById(R.id.actvDemo);
        actvDemo.setAdapter(actvDemoAdapter);

        actvDemoAdapter = new ArrayAdapter<>(this,R.layout.item_actv_demo);
        actvDemoAdapter.add("测试1");
        actvDemoAdapter.add("测试2");
        actvDemoAdapter.add("测试3");
        actvDemoAdapter.add("样式1");
        actvDemoAdapter.add("样式2");
        actvDemo.setAdapter(actvDemoAdapter);

//        actvDemo = new ArrayAdapter<String>(this, R.layout.item_actv_demo) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                convertView = LayoutInflater.from(parent.getContext()).
//                        inflate(R.layout.item_actv_demo, null);
//                TextView tvKeyword = (TextView) convertView;
//                return convertView;
//            }
//        };
    }

}
