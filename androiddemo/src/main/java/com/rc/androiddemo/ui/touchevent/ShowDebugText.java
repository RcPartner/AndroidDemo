package com.rc.androiddemo.ui.touchevent;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-06-29 18:27
 */
public class ShowDebugText {

    private static ShowDebugText instance;

    private StringBuilder content;

    private ShowDebugText() {
        content = new StringBuilder();
    }

    public ShowDebugText getInsance() {
        if (instance == null) {
            instance = new ShowDebugText();
        }
        return instance;
    }

    public void addContent(boolean isClear, String text) {
        if (isClear) {
            content.delete(0, content.length());
        }
        content.append(text);
    }

}
