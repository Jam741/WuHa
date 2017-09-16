package com.pisces.android.wuha.function.main


import android.os.Bundle
import android.support.v4.view.ViewPager
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.home.HomeFragment
import com.pisces.android.wuha.function.mine.MineFragment
import kotlinx.android.synthetic.main.main_act.*


class MainActivity : LBaseActivity() {

    private val tabs by lazy { arrayListOf("主页", "我的") }
    private val fragments by lazy({ arrayListOf(HomeFragment(), MineFragment()) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        viewPage.run {
            adapter = TabAdapter(supportFragmentManager, fragments, tabs)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    group_main.check(group_main.getChildAt(position).id)
                }
            })
        }

        group_main.setOnCheckedChangeListener { group, checkedId ->
            var pos = 0
            when (checkedId) {
                R.id.rad_home -> pos = 0
                R.id.rad_mine -> pos = 1
            }

            viewPage.setCurrentItem(pos, false)
        }
    }
}

