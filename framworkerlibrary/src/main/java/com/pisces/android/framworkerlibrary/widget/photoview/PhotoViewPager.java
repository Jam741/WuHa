package com.pisces.android.framworkerlibrary.widget.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jam on 16/9/7 下午6:13.
 * <p/>
 * * @date: 16/4/28 下午2:29
 * 解决连续缩放photoview报错问题
 * 主要原因是viewpager和photoview冲突
 * 解决方法:重写viewpager的onInterceptTouchEvent()和onTouchEvent()方法,将其try{}catch(){}
 */
public class PhotoViewPager extends ViewPager {


    private boolean isLocked;

    public PhotoViewPager(Context context) {
        super(context);
        isLocked = false;
    }

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isLocked) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !isLocked && super.onTouchEvent(event);
    }

    public void toggleLock() {
        isLocked = !isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }
}
