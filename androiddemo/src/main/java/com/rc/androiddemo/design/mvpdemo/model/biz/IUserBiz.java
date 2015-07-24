package com.rc.androiddemo.design.mvpdemo.model.biz;

/**
 * Description: 该类定义业务方法
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 09:48
 */
public interface IUserBiz {

    void login(String userName, String passWord, OnLoginListener onLoginListener);
}
