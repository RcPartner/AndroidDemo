package com.rc.androiddemo.ui.pulltorefresh;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-08-12 16:49
 */
public class PtrManager {

    int mHeaderHeight;

    int mFooterHeight;

    int offsetY;

    int totalOffsetY;

    int totalOffsetYAbs;

    int lastYPos;

    float pullOffsetRatio = 0.5f;

    int releaseDuration = 1000;

//    boolean isMoveUp;

    boolean isDownDirection;

    boolean isLoading;

    boolean isPulling;

    public void judgePullUpOrDown(float currentY) {
        int offset = (int) (currentY - lastYPos);
//        isMoveUp = offset < 0;
        isDownDirection = offset > 0;
    }

    public int moveOffset(float currentY) {
        int offset = (int) ((currentY - lastYPos) * pullOffsetRatio);
        lastYPos   = (int) currentY;
        totalOffsetY -= offset;
        totalOffsetYAbs = Math.abs(totalOffsetY);
        isPulling = true;
        return offset;
    }

    public boolean isMoveDown() {
        return totalOffsetY < 0;
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
