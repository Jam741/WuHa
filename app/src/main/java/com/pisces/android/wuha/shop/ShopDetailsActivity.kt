package com.pisces.android.wuha.shop

import android.os.Bundle


import android.view.View


import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter

import com.pisces.android.wuha.R

import kotlinx.android.synthetic.main.activity_shop_details.*



/**
 * Created by Chris Li on 2017/9/1.
 * 商品详情界面
 */

class ShopDetailsActivity : JBaseActivity(), View.OnClickListener {
    private val mTabList by lazy { arrayListOf("服务列表", "客户信息") }
    private val mViewList by lazy { arrayListOf(ServiceListFragment(), ClientMessageFragment()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        initView()
        setFullscreen()
    }

    private fun initView() {
        shop_return!!.setOnClickListener(this)
        view_pager.run {
            adapter = TabAdapter(supportFragmentManager, mViewList, mTabList)
        }
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.shop_return -> {
                finish()
            }

        }
    }
}
