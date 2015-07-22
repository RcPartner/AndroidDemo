package com.rc.androiddemo.ui.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rc.androiddemo.R;

import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * Description:
 * 相关链接：
 * 1) http://blog.sina.com.cn/s/blog_623868100101jlxz.html
 * 2) http://www.eoeandroid.com/thread-228664-1-1.html
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-03-04 16:41
 */
public class WebViewDemo extends Activity {

        private static final String URL = "http://www.baidu.com";
//    private static final String URL = "http://172.16.110.124:8080/cms-web/comm/index.html";
//    private static final String URL = "http://testshop.nzlm.cn:8088/shop/api/payment" +
//            "/yngateway/return_url.php?out_trade_no%3D760488914636922051%26extra_common_" +
//            "param%3Dproduct_buy%26mobile%3D1####";

    private WebView wvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_demo);
        wvDemo = (WebView) this.findViewById(R.id.wvDemo);

        WebSettings webSettings = wvDemo.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);//是否支持js
        webSettings.setSupportZoom(false);//是否可缩放
        webSettings.setUseWideViewPort(true);//设置适应移动设备大小
        webSettings.setLoadWithOverviewMode(true);

        wvDemo.addJavascriptInterface(new PayJs() {

            @Override
            @JavascriptInterface
            public void paySuccess() {
                System.out.println("paySuccesspaySuccesspaySuccesspaySuccess");
            }
        }, PayJs.JS_Name);

        wvDemo.setWebViewClient(new MyWebViewClient());
        wvDemo.loadUrl(URL);
    }

    /**
     * 设置多个cookie的方式
     */
    private void setCookies() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String strCookie;
        Cookie cookie;
        List<Cookie> cookieList = null;//多个cookies
        for (int i = 0; i < cookieList.size(); i++) {
            cookie = cookieList.get(i);
            strCookie = cookie.getName() + "=" + cookie.getValue() + ";"//键值对
                    + "domain=" + cookie.getDomain() + ";"//每个cookies都有的东西
                    + "path=" + cookie.getPath() + ";"
                    + "expiry=" + cookie.getExpiryDate();
            System.out.println(strCookie);
            cookieManager.setCookie(URL, strCookie);
        }
        wvDemo.loadUrl(URL);

    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (!url.equals(URL)) {
                wvDemo.stopLoading();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}
