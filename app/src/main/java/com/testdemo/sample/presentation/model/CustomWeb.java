package com.testdemo.sample.presentation.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

public final class CustomWeb extends WebView {

    private GestureDetector gestureDetector_traffictour;


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gestureDetector_traffictour.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
    public CustomWeb(Context context) {
        super(context);
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector_traffictour = gestureDetector;
    }
    public CustomWeb(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWeb(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}