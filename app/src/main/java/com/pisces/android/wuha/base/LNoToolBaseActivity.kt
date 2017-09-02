package com.pisces.android.wuha.base

import android.view.Window
import android.view.WindowManager

/**
 * Created by Chris Li on 2017/9/2.
 * 能隐藏状态栏的基础activity
 */
open class LNoToolBaseActivity:LBaseActivity(){

    /**
     * 设置隐藏标题栏 Chris Li
     */
    fun setNoTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    /**
     * 设置隐藏状态栏 Chris Li
     */
    fun setFullscreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}