package com.pisces.android.wuha.function.shop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.BodyForServiceDetailById
import com.pisces.android.wuha.entity.bean.ServiceDetailProvider
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import kotlinx.android.synthetic.main.activity_shop_details.*


/**
 * Created by Chris Li on 2017/9/1.
 * 商品详情界面
 */

class ShopDetailsActivity : LBaseActivity(), View.OnClickListener {
    companion object {
        private var id: String = ""
        private lateinit var context: Context
        fun start(context: Context, id: String) {
            val intent = Intent(context, ShopDetailsActivity::class.java)
            context.startActivity(intent)
            this.context = context
            this.id = id
        }
    }

    private val mTabList by lazy { arrayListOf("服务列表", "客户信息") }
    private val mViewList by lazy { arrayListOf(ServiceListFragment(), ClientMessageFragment()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        initView()
        initData()

    }

    private fun initData() {
        HttpUtli.toSubscribe(Api.service.getServiceProviderDetail(BodyForServiceDetailById(id, Constant.getGpsX(), Constant.getGpsY())
        ), object : SimpleSubscriber<ServiceDetailProvider>(this) {
            override fun onSuccess(t: ServiceDetailProvider?) {
              if (t==null) return Unit
                Log.i("lyx",t.id)
                bindData(t)
            }
        })
    }

    private fun bindData(t: ServiceDetailProvider) {
        name.text=t.name
        site.text=t.serviceProviderAddress.mainAddressLine
        person.text=t.viewingCount.toString()+"人去过"
        val dis: Int = t.distance.toInt()
        if (dis > 1000) {
            distance.text=(dis/1000).toString()+"km"
        }else{
            distance.text=dis.toString()+"m"
        }

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
