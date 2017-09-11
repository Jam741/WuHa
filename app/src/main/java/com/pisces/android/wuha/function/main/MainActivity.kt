package com.pisces.android.wuha.function.main


import android.os.Bundle
import android.view.KeyEvent
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.search.SearchFragment
import kotlinx.android.synthetic.main.main_act.*

//import kotlinx.android.synthetic.main.main_header.*


class MainActivity : LBaseActivity() {

    private val tabs by lazy { arrayListOf("主页", "我的") }
    private val fragments by lazy({ arrayListOf(HomeFragment(), PersonageFragment()) })
    private val searchFragment by lazy { SearchFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        viewPage.run {
            adapter = TabAdapter(supportFragmentManager, fragments, tabs)
        }
        tab.setupWithViewPager(viewPage)
//        btnSearch.setOnClickListener { showSearchLayout(true) }
    }


    /**
     * 显示搜索布局
     *
     * @param show 是否显示
     */
    fun showSearchLayout(show: Boolean) {
        if (show) {
            if (supportFragmentManager.findFragmentById(R.id.container_search) == null)
                supportFragmentManager.beginTransaction().add(R.id.container_search, searchFragment).commit()
            else
                supportFragmentManager.beginTransaction().show(searchFragment)
        } else {
            if (supportFragmentManager.findFragmentById(R.id.container_search) != null)
                supportFragmentManager.beginTransaction().hide(searchFragment)
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!searchFragment.isHidden) {
                showSearchLayout(false)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }


}

