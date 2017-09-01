package com.pisces.android.wuha.setting

import android.os.Bundle

import com.pisces.android.framworkerlibrary.core.JBaseActivity

import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/1.
 * 给我评分界面
 */

class GradeActivity : JBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)
        setToolbarTitle("给我评分")
    }
}
