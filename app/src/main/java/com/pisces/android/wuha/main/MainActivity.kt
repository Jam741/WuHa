package com.pisces.android.wuha.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.PlaceHolderFragment
import com.pisces.android.wuha.R
import com.pisces.android.wuha.pages.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_frag.*

class MainActivity : JBaseActivity() {

    private val tabs by lazy { arrayListOf("主页", "我的") }
    private val fragments by lazy { arrayListOf(HomeFragment(),PlaceHolderFragment.newInstance("我的")) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPage.run {
            adapter = TabAdapter(supportFragmentManager,fragments,tabs)
        }
        tab.setupWithViewPager(viewPage)
    }


}
