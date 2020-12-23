package com.lintschool.communi.event.scallinglayout;

public interface ScalingLayoutListener {

    void onCollapsed();

    void onExpanded();

    void onProgress(float progress);
}
