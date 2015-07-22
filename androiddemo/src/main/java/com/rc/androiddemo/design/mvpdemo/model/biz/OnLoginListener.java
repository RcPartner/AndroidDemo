package com.rc.androiddemo.design.mvpdemo.model.biz;


import com.rc.androiddemo.design.mvpdemo.model.bean.User;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 09:53
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
