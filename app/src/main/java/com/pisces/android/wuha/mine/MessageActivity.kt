package com.pisces.android.wuha.mine

import android.os.Bundle

import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/1.
 * 意见建议界面
 */

class MessageActivity : JBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setToolbarTitle("意见建议")
    }
}
