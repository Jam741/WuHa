package com.pisces.android.wuha.function

import android.os.Bundle
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/2.
 * 搜索界面
 */
class SearchForActivity : JBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for)
        setToolbarTitle("搜索")
    }
}