package com.pisces.android.wuha.function.shop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.activity_shop_details.*


/**
 * Created by Chris Li on 2017/9/1.
 * 商品详情界面
 */

class ShopDetailsActivity : LBaseActivity(), View.OnClickListener {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ShopDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mTabList by lazy { arrayListOf("服务列表", "客户信息") }
    private val mViewList by lazy { arrayListOf(ServiceListFragment(), ClientMessageFragment()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        initView()
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
