package com.rc.androiddemo.design.mvpdemo.presenter;

import android.os.Handler;

import com.rc.androiddemo.design.mvpdemo.model.bean.User;
import com.rc.androiddemo.design.mvpdemo.model.biz.IUserBiz;
import com.rc.androiddemo.design.mvpdemo.model.biz.OnLoginListener;
import com.rc.androiddemo.design.mvpdemo.model.biz.UserBiz;
import com.rc.androiddemo.design.mvpdemo.view.IUserLoginView;


/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 10:13
 */
public class LoginPresenter {

    private IUserLoginView iUserLoginView;

    private IUserBiz iUserBiz;

    private Handler handler = new Handler();

    public LoginPresenter(IUserLoginView iUserLoginView) {
        this.iUserLoginView = iUserLoginView;
        this.iUserBiz = new UserBiz();
    }

    public void login() {
        iUserLoginView.showLoading();
        iUserBiz.login(iUserLoginView.getUserName(), iUserLoginView.getPassWord(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.toMainActivity(user);
                        iUserLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.showFailedError();
                        iUserLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        iUserLoginView.clearUserName();
        iUserLoginView.clearPassWord();
    }
}