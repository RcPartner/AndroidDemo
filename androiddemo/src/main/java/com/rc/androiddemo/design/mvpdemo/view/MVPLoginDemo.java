package com.rc.androiddemo.design.mvpdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rc.androiddemo.R;
import com.rc.androiddemo.design.mvpdemo.model.bean.User;
import com.rc.androiddemo.design.mvpdemo.presenter.LoginPresenter;


/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 09:28
 */
public class MVPLoginDemo extends Activity implements IUserLoginView {

    private EditText etUserName, etPassWord;

    private Button btnLogin, btnClear;

    private ProgressBar pbLoading;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        loginPresenter = new LoginPresenter(this);

        initViews();
    }

    private void initViews() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassWord = (EditText) findViewById(R.id.etPassWord);
        btnLogin   = (Button) findViewById(R.id.btnLogin);
        btnClear   = (Button) findViewById(R.id.btnClear);
        pbLoading  = (ProgressBar) findViewById(R.id.pbLoading);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassWord() {
        return etPassWord.getText().toString();
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void clearUserName() {
        etUserName.setText("");
    }

    @Override
    public void clearPassWord() {
        etPassWord.setText("");
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUserName() + "Login Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }
}
