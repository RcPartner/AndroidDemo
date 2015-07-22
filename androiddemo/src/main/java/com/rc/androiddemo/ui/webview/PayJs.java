package com.rc.androiddemo.ui.webview;

import android.webkit.JavascriptInterface;

/**
 * Description: 支付的js
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-01-21 16:55
 */
public abstract class PayJs {

    public static final String JS_Name = "Pay";

    /**
     * 支付成功后的js调用
     */
    @JavascriptInterface
    public abstract void paySuccess();
}
