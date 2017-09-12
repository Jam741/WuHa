package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity

/**
 * Created by Chris Li on 2017/9/2.
 * 搜索界面
 */
class SearchForActivity : LBaseActivity() {

    companion object {
        fun start(context: Context) {
            val stater = Intent(context, SearchForActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_frag)
//        setToolbarTitle("搜索")
    }
}