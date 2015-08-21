package com.rc.androiddemo.ui.pulltorefresh;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-12 16:49
 */
public class PtrManager {

    int mHeaderHeight;

    int offsetY;

    int totalOffsetY;

    int lastTotalOffsetY;

    int lastYPos;

    float pullOffsetRatio = 0.5f;

    long releaseDuration = 1500l;

    boolean isMoveUp;

    boolean isMoveDown;

    boolean isLoading;

    boolean isPulling;

    public int moveOffset(float currentY) {
        int offset = (int) ((currentY - lastYPos) * pullOffsetRatio);
        lastYPos   = (int) currentY;
        totalOffsetY -= offset;
        isPulling = true;
        isMoveUp = offset < 0;
        isMoveDown = offset > 0;
        return offset;
    }

//    public float getPullOffset() {
//        return totalOffsetY;
//    }
//
//    public int getmHeaderHeight() {
//        return mHeaderHeight;
//    }
//
//    public void setmHeaderHeight(int mHeaderHeight) {
//        this.mHeaderHeight = mHeaderHeight;
//    }
//
//    public int getOffsetY() {
//        return offsetY;
//    }
//
//    public void setOffsetY(int offsetY) {
//        this.offsetY = offsetY;
//    }
//
//    public int getTotalOffsetY() {
//        return totalOffsetY;
//    }
//
//    public void setTotalOffsetY(int totalOffsetY) {
//        this.totalOffsetY = totalOffsetY;
//    }
//
//    public int getLastTotalOffsetY() {
//        return lastTotalOffsetY;
//    }
//
//    public void setLastTotalOffsetY(int lastTotalOffsetY) {
//        this.lastTotalOffsetY = lastTotalOffsetY;
//    }
//
//    public int getLastYPos() {
//        return lastYPos;
//    }
//
//    public void setLastYPos(int lastYPos) {
//        this.lastYPos = lastYPos;
//    }
}
