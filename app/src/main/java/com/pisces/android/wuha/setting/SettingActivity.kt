package com.pisces.android.wuha.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by Chris Li on 2017/8/31.
 * 设置界面
 */

class SettingActivity : LBaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setToolbarTitle("设置")
        initView()
    }

    private fun initView() {
        about_us!!.setOnClickListener(this)
        grade!!.setOnClickListener(this)
        version!!.setOnClickListener(this)
        report!!.setOnClickListener(this)
        setting_return!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.about_us -> {
                startActivity(Intent(this, AboutUsActivity::class.java))
            }
            R.id.grade -> {
                startActivity(Intent(this, GradeActivity::class.java))
            }
            R.id.version -> {
                startActivity(Intent(this, VersionActivity::class.java))
            }
            R.id.report -> {
                startActivity(Intent(this, ReportActivity::class.java))
            }
            R.id.setting_return -> {
                Toast.makeText(this, "点击了退出", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
