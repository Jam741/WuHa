package com.pisces.android.wuha.function.main.personage

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/1.
 * 意见建议界面
 */

class MessageActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MessageActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setToolbarTitle("意见建议")
    }
}
