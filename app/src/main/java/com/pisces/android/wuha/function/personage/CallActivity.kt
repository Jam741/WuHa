package com.pisces.android.wuha.function.personage

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by Chris Li on 2017/9/1.
 * 联系我们界面
 */

class CallActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CallActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        toolbar.setOnClickListener { finish() }
        topTitle.text = "联系我们"

    }
}
