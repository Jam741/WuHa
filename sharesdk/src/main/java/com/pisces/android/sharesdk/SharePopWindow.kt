package com.pisces.android.sharesdk

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.app.Activity
import android.view.WindowManager


/**
 * Created by jamisonline on 2017/9/24.
 */
class SharePopWindow(val activity: Activity, shareEventListener: ShareEventListener) : PopupWindow(activity) {

    val rootView by lazy {
        LayoutInflater.from(activity).inflate(R.layout.share_popwindow_layout, null).apply {
            findViewById(R.id.btnToWxFriends).setOnClickListener { shareEventListener.toWxFirends() }
            findViewById(R.id.btnToWxCircle).setOnClickListener { shareEventListener.toWxCricle() }
            findViewById(R.id.btnToQQFriends).setOnClickListener { shareEventListener.toQQFirends() }
            findViewById(R.id.btnToQQZone).setOnClickListener { shareEventListener.toQQZone() }
            findViewById(R.id.btnToWeiBo).setOnClickListener { shareEventListener.toWeibo() }
        }
    }

    init {
        setPopupWindow()
    }

    override fun dismiss() {
        super.dismiss()
        // popupWindow隐藏时恢复屏幕正常透明度
        setBackgroundAlpha(1.0f)
    }

    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        setBackgroundAlpha(0.5f)//设置屏幕透明度
        super.showAtLocation(parent, gravity, x, y)
    }


    /**
     * 设置窗口的相关属性
     */
    private fun setPopupWindow() {
        this.contentView = rootView// 设置View
        this.width = ViewGroup.LayoutParams.MATCH_PARENT// 设置弹出窗口的宽
        this.height = ViewGroup.LayoutParams.WRAP_CONTENT// 设置弹出窗口的高
        this.isFocusable = true// 设置弹出窗口可
        this.animationStyle = R.style.mypopwindow_anim_style// 设置动画
        this.setBackgroundDrawable(ColorDrawable(0x00000000))// 设置背景透明
        rootView.setOnTouchListener({ v, event ->
            // 如果触摸位置在窗口外面则销毁
            val height = rootView.findViewById(R.id.id_pop_layout).top
            val y = event.y.toInt()
            if (event.action == MotionEvent.ACTION_UP) {
                if (y < height) {
                    this.dismiss()
                }
            }
            return@setOnTouchListener true
        })
        rootView.findViewById(R.id.btnCancel).setOnClickListener { this.dismiss() }
    }


    /**
     * 设置添加屏幕的背景透明度

     * @param bgAlpha
     * *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    fun setBackgroundAlpha(bgAlpha: Float) {
        val lp = (activity).window
                .attributes
        lp.alpha = bgAlpha
        (activity).window.attributes = lp
    }

}