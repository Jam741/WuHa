package com.pisces.android.wuha.function.collect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.wuha.Config

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.shop.ShopDetailsActivity

import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.ListUtil
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏界面
 */

class CollectActivity : LBaseActivity() {
    val mAdapter by lazy { createAdapter() }
    private fun createAdapter(): CommonRecyclerAdapter<ServiceProvider> {
        return object : CommonRecyclerAdapter<ServiceProvider>(this, null, R.layout.i_collect) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceProvider, position: Int) {
                holder.run {
                    setTextWith(R.id.i_collect_name, t.name)
                    setTextWith(R.id.i_collect_person, t.startingPrice.toString() + "人去过")
                    setTextWith(R.id.i_collect_site, t.serviceProviderAddress.mainAddressLine)
                    setTextWith(R.id.i_collect_price, "￥" + t.startingPrice)

                    val dis: Int = t.distance.toInt()
                    if (dis > 1000) {
                        setTextWith(R.id.i_collect_distance, (dis / 1000).toString() + "km")
                    } else {
                        setTextWith(R.id.i_collect_distance, dis.toString() + "m")
                    }

                    setImageUrl(R.id.i_collect_img, t.serviceProviderCertificationInfo.legalPersonInfoImagePath)

                    holder.setOnItemClickListener(object : RecyclerViewHolder.OnItemCliceListener {
                        override fun itemClick(itemView: View, position: Int) {
                            ShopDetailsActivity.start(this@CollectActivity, t.id)
                        }
                    })
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CollectActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        toolbar.setOnClickListener {
            finish()
        }
        topTitle.text = "我的收藏"

        recycler_view.run {
            loadData()
            layoutManager = LinearLayoutManager(this@CollectActivity)
            adapter = mAdapter
        }

    }

    private fun loadData() {
        HttpUtli.toSubscribe(Api.service.getUserFavorites(BodyCollect("1", Constant.getGpsX(), Constant.getGpsY())), object : SimpleSubscriber<ArrayList<ServiceProvider>>(this) {
            override fun onSuccess(t: ArrayList<ServiceProvider>?) {
                if (t == null) return Unit
                mAdapter.refresh(t)
            }
        })
    }
}
