package com.rc.androiddemo.ui.flowlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rc.androiddemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-09-25 08:59
 */
public class FlowLayoutDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flotlayout_demo);
        List<String> tagList = new ArrayList<>();
        tagList.add("我是TAG1");
        tagList.add("我是一个TAG");
        tagList.add("我是一个TAG我是一个TAG");
        tagList.add("我是一asf个TAG我是一个TAG");
        tagList.add("我是一个TAG");
        BaseTagAdapter<String> adapter = new BaseTagAdapter<String>(){
            @Override
            public String getItem(int position) {
                return null;
            }

            @Override
            long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(FlowLayout flowLayout, int position, String data) {
                TextView tv = new TextView(FlowLayoutDemo.this);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 10, 10, 10);
                tv.setPadding(10, 5, 10, 5);
                tv.setLayoutParams(lp);
                tv.setText(getDataList().get(position));
                tv.setTextColor(Color.BLUE);
                tv.setBackgroundResource(R.drawable.selector_tag_text);
                return tv;
            }
        };
        adapter.setDataList(tagList);
        ((FlowLayout) findViewById(R.id.flTag)).setAdapter(adapter);
        ((FlowLayout) findViewById(R.id.flTag)).setOnTagSelectListener(new FlowLayout.OnTagSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectSet, View view) {
                Toast.makeText(FlowLayoutDemo.this, selectSet.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
