package com.rc.androiddemo.ui.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.rc.androiddemo.R;

/**
 * Description:
 * User: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-04-17 09:44
 */
public class AlertDialogDemo extends Activity {

    private Button btnShow;
    private Button btnDismiss;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_demo);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnDismiss = (Button) findViewById(R.id.btnDismiss);

        Button button = new Button(this);
        button.setText("自定义的view");
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(100,100);

        button.setLayoutParams(lp);

        //要设置自定view的大小，只能在最外层套一层viewGroup
        View view = LayoutInflater.from(this).inflate(R.layout.custom_ad_demo,null);

        mAlertDialog = new AlertDialog.Builder(AlertDialogDemo.this).
                setTitle("测试").setMessage("消息体消息体").setView(view).create();


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.show();
                setWindowSize();
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAlertDialog.dismiss();
    }

    /**
     * 需要在调用show后设置才有效果
     */
    private void setWindowSize() {
        Window window = mAlertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;//起始x坐标
        lp.y = 0;//起始y坐标
        lp.width = 1000;//宽
        lp.height = 1000;//高
        //   lp.gravity = Gravity.CENTER;//位置
        lp.alpha = 0.5f;//透明度
        window.setAttributes(lp);
    }
}
