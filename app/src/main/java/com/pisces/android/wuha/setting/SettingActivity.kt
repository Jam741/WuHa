package com.pisces.android.wuha.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

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
        setToolbarTitle("设置")
        initView()
    }

    private fun initView() {
        about_us.setOnClickListener { AboutUsActivity.start(this) }
        grade.setOnClickListener { GradeActivity.start(this) }
        version.setOnClickListener { VersionActivity.start(this) }
        report.setOnClickListener { ReportActivity.start(this) }
        setting_return.setOnClickListener { Toast.makeText(this, "点击了退出", Toast.LENGTH_SHORT).show() }
    }


}
