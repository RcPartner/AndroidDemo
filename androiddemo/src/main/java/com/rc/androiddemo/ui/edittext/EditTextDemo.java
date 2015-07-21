package com.rc.androiddemo.ui.edittext;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-01-06 10:00
 */
public class EditTextDemo extends Activity {

    private EditText etDemo1;
    private EditText etDemo2;
    private Button btnClearError;

    protected void initView() {
        etDemo1 = (EditText) this.findViewById(R.id.etDemo1);
        etDemo2 = (EditText) this.findViewById(R.id.etDemo2);
        btnClearError = (Button) this.findViewById(R.id.btnClearError);

        btnClearError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearError();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edittext_demo);
        super.onCreate(savedInstanceState);
        initView();
        setError();
        setErrorWithDrawable();
    }

    private void setError() {
        etDemo1.setError("错误提示（文本）");
    }

    private void setErrorWithDrawable() {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 40, 40);//设置大小，否则不显示
        etDemo2.setError("错误提示（文本+图标）", drawable);
    }

    private void clearError() {
        etDemo1.setError(null);
        etDemo2.setError(null);
    }
}
