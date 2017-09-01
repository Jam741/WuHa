package com.pisces.android.wuha.mine

import android.os.Bundle

import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/1.
 * 分享app界面
 */

class ShareActivity : JBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        setToolbarTitle("分享app")
    }
}
