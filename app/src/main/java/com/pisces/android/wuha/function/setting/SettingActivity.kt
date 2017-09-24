package com.pisces.android.wuha.function.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.pisces.android.framworkerlibrary.utlis.AppUtils
import com.pisces.android.wuha.App

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.user.UserController
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by Chris Li on 2017/8/31.
 * 设置界面
 */

class SettingActivity : LBaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        toolbar.setOnClickListener { finish() }
        topTitle.text = "设置"
        about_us.setOnClickListener { AboutUsActivity.start(this) }
        grade.setOnClickListener {
            try {
                val uri = Uri.parse("market://details?id=" + packageName)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this@SettingActivity, "您的手机没有安装Android应用市场", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
        version.run {
            text = "v\t" + AppUtils.getVersionName(this@SettingActivity)
        }
//        version.setOnClickListener { VersionActivity.start(this) }
        setting_return.setOnClickListener {
            UserController.loginOut(this)
            close()
        }
    }


}
