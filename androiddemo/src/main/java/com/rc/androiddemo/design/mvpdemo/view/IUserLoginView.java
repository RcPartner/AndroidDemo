package com.rc.androiddemo.design.mvpdemo.view;


import com.rc.androiddemo.design.mvpdemo.model.bean.User;

/**
 * Description: Presenter与View之间通过接口交互 该类定义View中有哪些方法
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 10:00
 */
public interface IUserLoginView {

    /**
     * 获取账号
     * @return
     */
    String getUserName();

    /**
     * 获取密码
     * @return
     */
    String getPassWord();

    /**
     * 显示加载进度条
     */
    void showLoading();

    /**
     * 隐藏加载进度条
     */
    void hideLoading();

    /**
     * 登录成功跳转
     * @param user
     */
    void toMainActivity(User user);

    /**
     * 登录失败提示
     */
    void showFailedError();

    /**
     * 清空账号
     */
    void clearUserName();

    /**
     * 清空密码
     */
    void clearPassWord();
}
