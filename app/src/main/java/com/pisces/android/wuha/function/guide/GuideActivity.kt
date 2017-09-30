package com.pisces.android.wuha.function.guide

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.RequiresApi
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.*
import android.widget.ImageView
import com.pisces.android.framworkerlibrary.utlis.SPUtils
import com.pisces.android.wuha.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.main.MainActivity
import kotlinx.android.synthetic.main.guide_act.*

/**
 * Created by Jam on 2017/9/29.
 */
class GuideActivity : LBaseActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, GuideActivity::class.java)
            context.startActivity(starter)
        }

        fun setViewGuide(context: Context, viewed: Boolean) {
            SPUtils.put(context, Constant.KEY_VIEW_GUIDE, viewed)
        }

        fun isViewGuide(context: Context): Boolean {
            return SPUtils.get(context, Constant.KEY_VIEW_GUIDE, false) as Boolean
        }
    }


    private var isLastPage: Boolean = false
    private var isDragPage: Boolean = false
    private var canJumpPage = true
    private val views by lazy { createViews() }
    private var imagesRes = arrayListOf(R.mipmap.defoult_pic1, R.mipmap.defoult_pic2, R.mipmap.defoult_pic3)

    private fun createViews(): ArrayList<View> {
        val views = ArrayList<View>()

        imagesRes.mapTo(views) {
            LayoutInflater.from(this).inflate(R.layout.item_guide, null).apply { (findViewById(R.id.iv_image) as ImageView).setImageResource(it) }
        }
        return views
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guide_act)

        setViewGuide(this, true)
        initViews()
    }


    private fun initViews() {
        vp_guidance.adapter = MyPageAdapter()
        vp_guidance.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.v("AAA", isLastPage.toString() + "   " + isDragPage + "   " + positionOffsetPixels)
                if (isLastPage && isDragPage && positionOffsetPixels == 0) {   //当前页是最后一页，并且是拖动状态，并且像素偏移量为0
                    if (canJumpPage) {
                        canJumpPage = false
                        JumpToNext()
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                isLastPage = position == views.size - 1
                rv_point.check(rv_point.getChildAt(position).id)
            }

            override fun onPageScrollStateChanged(state: Int) {
                isDragPage = state == 1

            }
        })

    }


    private fun JumpToNext() {
        close()
        MainActivity.statr(this)
    }


    internal inner class MyPageAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return views.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(views[position])
            return views[position]
        }

        override fun getItemPosition(`object`: Any?): Int {
            return views.indexOf(`object`)
        }

    }
}