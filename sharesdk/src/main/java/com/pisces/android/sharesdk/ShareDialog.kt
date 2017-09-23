package com.pisces.android.sharesdk

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.sina.weibo.sdk.api.WeiboMultiMessage
import com.sina.weibo.sdk.share.WbShareHandler
import com.tencent.connect.share.QQShare
import com.tencent.tauth.Tencent
import kotlinx.android.synthetic.main.share_layout.*
import com.sina.weibo.sdk.api.TextObject
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.sina.weibo.sdk.api.ImageObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


/**
 * Created by Jam on 2017/9/23.
 */
class ShareDialog(activity: Activity, private val shareEventListener: ShareEventListener) : AlertDialog(activity) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_layout)
        initEvent()
    }

    private fun initEvent() {
        btnToWxFriends.setOnClickListener { shareEventListener.toWxFirends() }
        btnToWxCircle.setOnClickListener { shareEventListener.toWxCricle() }
        btnToQQFriends.setOnClickListener { shareEventListener.toQQFirends() }
        btnToQQZone.setOnClickListener { shareEventListener.toQQZone() }
        btnToWeiBo.setOnClickListener { shareEventListener.toWeibo() }
    }


    interface ShareEventListener {
        fun toWxFirends()
        fun toWxCricle()
        fun toQQFirends()
        fun toQQZone()
        fun toWeibo()
    }


}