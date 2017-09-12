package com.pisces.android.wuha.function.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/2.
 * 版本号界面
 */
class VersionActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VersionActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version)
    }

}