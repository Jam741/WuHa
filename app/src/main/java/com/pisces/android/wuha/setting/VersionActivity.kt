package com.pisces.android.wuha.setting

import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/2.
 * 版本号界面
 */
class VersionActivity : LBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version)
        setToolbarTitle("版本号")
    }

}