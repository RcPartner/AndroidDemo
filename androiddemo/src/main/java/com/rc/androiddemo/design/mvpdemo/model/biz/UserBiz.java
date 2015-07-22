package com.rc.androiddemo.design.mvpdemo.model.biz;


import com.rc.androiddemo.design.mvpdemo.model.bean.User;

/**
 * Description: 该类定义用户登录业务方法
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-24 09:54
 */
public class UserBiz implements IUserBiz {

    @Override
    public void login(final String userName, final String passWord, final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("wrq".equals(userName) && "123".equals(passWord)){
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassWord(passWord);
                    loginListener.loginSuccess(user);
                } else{
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
