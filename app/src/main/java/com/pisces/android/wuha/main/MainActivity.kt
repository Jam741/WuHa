package com.pisces.android.wuha.main


import android.os.Bundle
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LNoToolBaseActivity
import com.pisces.android.wuha.pages.HomeFragment
import com.pisces.android.wuha.pages.PersonageFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : LNoToolBaseActivity() {

    private val tabs by lazy { arrayListOf("主页", "我的") }
    private val fragments by lazy({ arrayListOf(HomeFragment(), PersonageFragment()) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPage.run {
            adapter = TabAdapter(supportFragmentManager, fragments, tabs)
        }
        tab.setupWithViewPager(viewPage)
        setFullscreen()
    }

    public data class Test(var ServiceProviderType: Int, var CurrentLatitude: Float, var CurrentLongitude: Float, var CurrentPageIndex: Int, var NumberOfResultsPerPage: Int)

}

