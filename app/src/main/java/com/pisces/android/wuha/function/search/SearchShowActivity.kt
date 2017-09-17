package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.home.ServiceProviderPresenter
import com.pisces.android.wuha.function.shop.ShopDetailsActivity
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.f_service.*
import kotlinx.android.synthetic.main.search_header.*

/**
 * Created by Chris Li on 2017/9/17.
 * 搜索的产品展示界面
 */
class SearchShowActivity : LBaseActivity() {

    val mAdapter by lazy { createAdapter() }
    var way: Int = 1


    private fun createAdapter(): CommonRecyclerAdapter<ServiceProvider> {
        return object : CommonRecyclerAdapter<ServiceProvider>(this, null, R.layout.i_medical) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceProvider, position: Int) {
                holder.run {
                    setTextWith(R.id.i_medical_name, t.name)
                    setTextWith(R.id.i_medical_site, t.serviceProviderAddress.mainAddressLine)
                    setTextWith(R.id.i_medical_price, "￥" + t.startingPrice)
                    setTextWith(R.id.i_medical_person, t.viewingCount.toString() + "人去过")
                    var dis: Int = t.distance.toInt()
                    if (dis > 1000) {
                        setTextWith(R.id.i_medical_distance, (dis / 1000).toString() + "km")
                    } else {
                        setTextWith(R.id.i_medical_distance, dis.toString() + "m")
                    }
                    setImageUrl(R.id.i_medical_img, t.serviceProviderCertificationInfo.legalPersonInfoImagePath)
                }
                holder.setOnItemClickListener(object : RecyclerViewHolder.OnItemCliceListener {
                    override fun itemClick(itemView: View, position: Int) {
                        ShopDetailsActivity.start(this@SearchShowActivity, t.id)
                    }
                })
            }
        }
    }

    var page: Int = 1

    companion object {
        var keyword: String = ""
        fun start(context: Context, keyword: String) {
            val stater = Intent(context, SearchShowActivity::class.java)
            context.startActivity(stater)
            this.keyword = keyword

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_show_a)
        edSearchView.run {
        }
        edSearchView.setOnClickListener {
            SearchForActivity.start(this)
            finish()
        }
        recycler_view.run {
            layoutManager = LinearLayoutManager(this@SearchShowActivity)
            adapter = mAdapter

            setPullRefreshEnabled(false)
            setLoadingMoreEnabled(true)
            setLoadingListener(object : XRecyclerView.LoadingListener {
                override fun onRefresh() {
                    page = 1
                    loadData()

                }

                override fun onLoadMore() {
                    page++
                    loadData()
                }
            })
        }

//        radio_group.run {
//            check(R.id.rad_distance)
//            setOnCheckedChangeListener { group, checkedId ->
//                when (checkedId) {
//                    R.id.rad_distance -> {
//                        way = 1
//                        page = 1
//                        loadData()
//                    }
//                    R.id.rad_rq -> {
//                        page = 1
//                        way = 2
//                        loadData()
//                    }
//
//                    R.id.rad_price -> {
//                        page = 1
//                        way = 3
//                        loadData()
//                    }
//
//                }
//
//            }
//        }

        loadData()
    }


    private fun loadData() {
        when (way) {
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }

        }

        HttpUtli.toSubscribe(Api.service.queryServiceProviderBySearceName(BodySearch(keyword, page, Config.pageSize)), object : ProgressSubscriber<ArrayList<ServiceProvider>>(this) {
            override fun onSuccess(t: ArrayList<ServiceProvider>?) {
                if (t == null) return Unit
                mAdapter.refresh(t)
            }
        })
    }
}