package com.pisces.android.wuha.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/2.
 * 关于我们界面
 */
class AboutUsActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AboutUsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setToolbarTitle("关于我们")
    }

}