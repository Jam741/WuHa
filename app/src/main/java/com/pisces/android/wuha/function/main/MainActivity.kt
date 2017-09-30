package com.pisces.android.wuha.function.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.home.HomeFragment
import com.pisces.android.wuha.function.mine.MineFragment
import kotlinx.android.synthetic.main.main_act.*
import java.util.*


class MainActivity : LBaseActivity() {


    companion object {

        fun statr(context: Context) {
            val stater = Intent(context, MainActivity::class.java)
            context.startActivity(stater)
        }
    }

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

    /**
     * 双击退出函数
     */
    private var isExit: Boolean = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click()
        }
        return false
    }

    private fun exitBy2Click() {
        val tExit: Timer
        if (!isExit) {
            isExit = true // 准备退出
            toastWith("再按一次退出程序")
            tExit = Timer()
            tExit.schedule(object : TimerTask() {
                override fun run() {
                    isExit = false // 取消退出
                }
            }, 2000) // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(0)
        }
    }
}

