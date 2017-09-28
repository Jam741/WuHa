package com.pisces.android.sharesdk

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.share_dialog_layout.*


/**
 * Created by Jam on 2017/9/23.
 */
class ShareDialog(activity: Activity, private val shareEventListener: ShareEventListener) : AlertDialog(activity) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_dialog_layout)
        initEvent()
    }

    private fun initEvent() {
        btnToWxFriends.setOnClickListener { shareEventListener.toWxFirends() }
        btnToWxCircle.setOnClickListener { shareEventListener.toWxCricle() }
        btnToQQFriends.setOnClickListener { shareEventListener.toQQFirends() }
        btnToQQZone.setOnClickListener { shareEventListener.toQQZone() }
        btnToWeiBo.setOnClickListener { shareEventListener.toWeibo() }
    }


}