package com.pisces.android.wuha.function.main.personage.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/1.
 * 举报投诉界面
 */

class ReportActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ReportActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        setToolbarTitle("举报投诉")
    }
}
