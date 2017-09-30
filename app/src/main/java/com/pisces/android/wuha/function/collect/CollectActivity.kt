package com.pisces.android.wuha.function.collect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.shop.ShopDetailsActivity
import com.pisces.android.wuha.function.user.UserController
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏界面
 */

class CollectActivity : LBaseActivity(), CollectContract.View {


    val mAdapter by lazy { createAdapter() }

    val userId by lazy { UserController.getUserInfoBean(this)!!.id }

    val bodyForCollect by lazy { BodyCollect(userId) }

    val presenter by lazy { CollectPresenter(this, this) }


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CollectActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        topLeft.setOnClickListener { close() }
        topTitle.text = "我的收藏"

        recycler_view.run {
            layoutManager = LinearLayoutManager(this@CollectActivity)
            adapter = mAdapter
        }

        presenter.getCollectList(bodyForCollect)
    }

    override fun onResponse(data: ArrayList<ServiceProvider>) {
        mAdapter.refresh(data)
    }

    override fun onLoadCompleted(page: Int, empty: Boolean) {
    }

    override fun showEmpty(empty: Boolean) {
        empty_layout.visibility = if (empty) View.VISIBLE else View.GONE
        recycler_view.visibility = if (!empty) View.VISIBLE else View.GONE
    }


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
}
