package com.pisces.android.wuha.function.shop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.pisces.adnroid.ltaskpicture.LImg
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.BodyForServiceDetailById
import com.pisces.android.wuha.entity.bean.ServiceDetailProvider
import com.pisces.android.wuha.function.user.UserController
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import kotlinx.android.synthetic.main.activity_shop_details.*


/**
 * Created by Chris Li on 2017/9/1.
 * 商铺详情界面
 */

class ShopDetailsActivity : LBaseActivity(), View.OnClickListener {
    var isCollect: Boolean = false

    val serviceListFragment by lazy { ServiceListFragment() }

    val clientMessageFragment by lazy { ClientMessageFragment() }

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
    private val mViewList by lazy { arrayListOf(serviceListFragment, clientMessageFragment) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        initView()
        initData()

        collect.setOnClickListener {
            if (!isCollect) {
                if (!UserController.passPrecondition(this)) return@setOnClickListener
                HttpUtli.toSubscribe(Api.service.addUserFavorite(BodyForCollect(UserController.userId(this).toString(), id)), object : SimpleSubscriber<Any>(this) {
                    override fun onSuccess(t: Any?) {
                        if (t == null) return Unit
                        collect.setImageResource(R.mipmap.mine_icon_collect)
                        isCollect = true
                        toastWith("该商铺加入收藏")
                    }
                })
            } else {

                if (!UserController.passPrecondition(this)) return@setOnClickListener
                HttpUtli.toSubscribe(Api.service.cancelCollect(BodyForCollect(UserController.userId(this).toString(), id)), object : ProgressSubscriber<Int>(this) {
                    override fun onSuccess(t: Int?) {
                        collect.setImageResource(R.mipmap.home_icon_collect)
                        isCollect = false
                        toastWith("该商铺移出收藏")
                    }
                })

            }
        }

    }

    private fun initData() {
        HttpUtli.toSubscribe(Api.service.getServiceProviderDetail(BodyForServiceDetailById(id, Constant.getGpsY(), Constant.getGpsX())
        ), object : SimpleSubscriber<ServiceDetailProvider>(this) {
            override fun onSuccess(t: ServiceDetailProvider?) {
                if (t == null) return Unit
                Log.i("lyx", t.id)
                bindData(t)
                serviceListFragment.setData(t.serviceProviderServiceCategories)
                clientMessageFragment.setData(t.serviceProviderIntroduction)
            }
        })
    }

    private fun bindData(t: ServiceDetailProvider) {
        var img: ImageView = findViewById(R.id.img_bg) as ImageView
        LImg.with(this).load(t.serviceProviderIntroduction.imagePath).into(img)
        name.text = t.name
        site.text = t.serviceProviderAddress.mainAddressLine
        person.text = t.viewingCount.toString() + "人去过"
        val dis: Int = t.distance.toInt()
        if (dis > 1000) {
            distance.text = (dis / 1000).toString() + "km"
        } else {
            distance.text = dis.toString() + "m"
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
