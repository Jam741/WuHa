package com.pisces.android.wuha.pages

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import com.pisces.android.framworkerlibrary.core.JBaseFragment

import com.pisces.android.wuha.R
import com.pisces.android.wuha.collect.CollectActivity
import com.pisces.android.wuha.mine.CallActivity
import com.pisces.android.wuha.mine.MessageActivity
import com.pisces.android.wuha.mine.ShareActivity
import com.pisces.android.wuha.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_personage.*

/**
 * Created by Chris Li on 2017/8/31.
 * 我的界面
 */

class PersonageFragment : JBaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_personage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        setting!!.setOnClickListener(this)
        collect!!.setOnClickListener(this)
        message!!.setOnClickListener(this)
        call!!.setOnClickListener(this)
        share!!.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.setting//设置
            -> startActivity(Intent(activity, SettingActivity::class.java))
            R.id.collect//我的收藏
            -> startActivity(Intent(activity, CollectActivity::class.java))
            R.id.message//意见建议
            -> startActivity(Intent(activity, MessageActivity::class.java))
            R.id.call//联系我们
            -> startActivity(Intent(activity, CallActivity::class.java))
            R.id.share//分享app
            -> startActivity(Intent(activity, ShareActivity::class.java))
        }

    }
}
