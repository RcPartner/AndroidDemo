package com.rc.androiddemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rc.androiddemo.adapter.ListViewAdapter;
import com.rc.androiddemo.components.activity.FragmentTabHostDemo;
import com.rc.androiddemo.design.mvpdemo.view.MVPLoginDemo;
import com.rc.androiddemo.resources.drawable.bitmapfile.BitmapFileDemo;
import com.rc.androiddemo.resources.drawable.layerlist.LayerListDemo;
import com.rc.androiddemo.resources.drawable.ninepatchfile.NinePatchFileDemo;
import com.rc.androiddemo.service.ServiceDemo;
import com.rc.androiddemo.ui.animation.TweenedAnimation;
import com.rc.androiddemo.ui.animation.view.tween.AlphaDemo;
import com.rc.androiddemo.ui.animation.view.tween.AnimationDemo;
import com.rc.androiddemo.ui.animation.view.tween.RotateDemo;
import com.rc.androiddemo.ui.animation.view.tween.ScaleDemo;
import com.rc.androiddemo.ui.animation.view.tween.TranslateDemo;
import com.rc.androiddemo.ui.custom.TwoScrollViewDemo;
import com.rc.androiddemo.ui.dialog.AlertDialogDemo;
import com.rc.androiddemo.ui.dialog.DialogFragmentDemo;
import com.rc.androiddemo.ui.edittext.EditTextDemo;
import com.rc.androiddemo.ui.pulltorefresh.PullToRefreshDemo;
import com.rc.androiddemo.ui.ratingbar.RatingBarDemo;
import com.rc.androiddemo.ui.recyclerview.RcGridDemo;
import com.rc.androiddemo.ui.recyclerview.RcListDemo;
import com.rc.androiddemo.ui.recyclerview.RvStaggeredGridDemo;
import com.rc.androiddemo.ui.scroller.ScrollerDemo;
import com.rc.androiddemo.ui.switchh.SwitchDemo;
import com.rc.androiddemo.ui.textview.AutoCompleteTextViewDemo;
import com.rc.androiddemo.ui.textview.TextViewDemo;
import com.rc.androiddemo.ui.touchevent.TouchEventDemo;
import com.rc.androiddemo.ui.webview.WebViewDemo;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2014-12-24 10:20
 */
public class MainActivity extends Activity {

    private ListView lvDemo;
    private ListViewAdapter<String> lvDemoAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDemo = (ListView) findViewById(R.id.lvDemo);
        initData();
        initView();
//        String apkRoot = "chmod 777 " + getPackageCodePath();
//        RootCommand(apkRoot);

    }

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath(); RootCommand(apkRoot);
     * @return 应用程序是/否获取Root权限
     */

    public static boolean RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;

        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;

    }

    protected void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < cArray.length; i++) {
            list.add(cArray[i].getSimpleName());
        }

        lvDemoAdapter = new ListViewAdapter<String>(list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null) {
                    textView = new TextView(MainActivity.this);
                    textView.setPadding(getResources().getDimensionPixelOffset(R.dimen.item_mar_left),
                            getResources().getDimensionPixelOffset(R.dimen.item_mar_top),
                            getResources().getDimensionPixelOffset(R.dimen.item_mar_left),
                            getResources().getDimensionPixelOffset(R.dimen.item_mar_top));
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(18);
                    convertView = textView;
                } else {
                    textView = (TextView) convertView;
                }
                textView.setText(list.get(position));
                return convertView;
            }
        };
    }

    protected void initView() {
        lvDemo.setAdapter(lvDemoAdapter);
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, cArray[position]);
                startActivity(intent);
            }
        });
    }

    private Class cArray[] = {TextViewDemo.class, EditTextDemo.class, TweenedAnimation.class,
            AlphaDemo.class, RotateDemo.class, ScaleDemo.class, TranslateDemo.class,
            RatingBarDemo.class, BitmapFileDemo.class, LayerListDemo.class, NinePatchFileDemo.class,
            WebViewDemo.class, SwitchDemo.class, AlertDialogDemo.class, MVPLoginDemo.class,
            AnimationDemo.class, DialogFragmentDemo.class, TouchEventDemo.class, PullToRefreshDemo.class,
            ScrollerDemo.class, ServiceDemo.class};
            AnimationDemo.class, DialogFragmentDemo.class, TouchEventDemo.class, TwoScrollViewDemo.class,
            com.rc.androiddemo.ui.alpha.AlphaDemo.class, FragmentTabHostDemo.class,
            RcListDemo.class, RcGridDemo.class, RvStaggeredGridDemo.class, AutoCompleteTextViewDemo.class};

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
