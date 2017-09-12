package com.pisces.android.wuha.function.main


import android.os.Bundle
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.main_act.*


class MainActivity : LBaseActivity() {

    private val tabs by lazy { arrayListOf("主页", "我的") }
    private val fragments by lazy({ arrayListOf(HomeFragment(), PersonageFragment()) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        viewPage.run {
            adapter = TabAdapter(supportFragmentManager, fragments, tabs)
        }
        tab.setupWithViewPager(viewPage)
    }
}

