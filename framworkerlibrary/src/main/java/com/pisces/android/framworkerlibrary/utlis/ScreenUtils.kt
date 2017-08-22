package com.yingwumeijia.commonlibrary.utils


import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import com.pisces.android.framworkerlibrary.JBaseApplication


/**
 * Created by Jam on 2016/3/14.
 * 屏幕工具
 */
object ScreenUtils {

    //屏幕度量类
    private var metrics: DisplayMetrics? = null

    val displayMetrics: DisplayMetrics
        get() {
            if (metrics == null) {
                metrics = JBaseApplication.appResources().displayMetrics
            }
            return metrics!!
        }

    /**
     * 获取屏幕密度

     * @return
     */
    val density: Float
        get() {
            if (metrics == null) {
                displayMetrics
            }
            return metrics!!.scaledDensity
        }

    /**
     * 获取屏幕宽度像素

     * @return
     */
    val screenWidth: Int
        get() {
            if (metrics == null) {
                displayMetrics
            }
            return metrics!!.widthPixels
        }

    val screenHeight: Int
        get() {
            if (metrics == null) {
                displayMetrics
            }
            return metrics!!.heightPixels
        }

    fun px2db(pxValue: Float): Int {
        if (metrics == null) {
            displayMetrics
        }
        return (pxValue / metrics!!.density + 0.5f).toInt()
    }

    fun dp2px(dpValue: Float, context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2sp(pxValue: Float): Int {
        if (metrics == null) {
            displayMetrics
        }
        return (pxValue / metrics!!.scaledDensity + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Int {
        if (metrics == null) {
            displayMetrics
        }
        return (spValue * metrics!!.scaledDensity + 0.5f).toInt()
    }

    fun getTextLength(textSize: Float, text: String): Float {
        val paint = Paint()
        paint.textSize = textSize
        return paint.measureText(text)
    }

    /**
     * 获取实际屏幕高度
     * 如 1920 * 1080

     * @param activity
     * *
     * @return
     */
    fun getRealMetrics(activity: Activity): IntArray {
        val dpi = IntArray(2)
        val display = activity.windowManager.defaultDisplay
        val dm = DisplayMetrics()
        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, dm)
            dpi[0] = dm.widthPixels
            dpi[1] = dm.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dpi
    }

    /**
     * 获取状态栏高度

     * @param activity
     * *
     * @return
     */
    fun getStatusHeight(activity: Activity): Int {
        val rect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        return rect.top
    }


    /**
     * @param view
     * *
     * @param scale height:width
     */
    fun setLayoutScaleByWidth(view: View, width: Int, scale: Float) {

        val sWidth: Float
        val sHeight: Float

        sWidth = width.toFloat()
        sHeight = sWidth * scale

        val layoutParams = view.layoutParams
        layoutParams.width = sWidth.toInt()
        layoutParams.height = sHeight.toInt()
        view.layoutParams = layoutParams
    }
}
