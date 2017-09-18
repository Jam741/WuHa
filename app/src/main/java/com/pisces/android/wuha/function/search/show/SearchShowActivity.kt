package com.pisces.android.wuha.function.search.show

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.search.BodySearch
import com.pisces.android.wuha.function.search.SearchForActivity
import com.pisces.android.wuha.function.shop.ShopDetailsActivity
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.ListUtil
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.search_header.*
import kotlinx.android.synthetic.main.search_show_a.*

/**
 * Created by Chris Li on 2017/9/17.
 * 搜索的产品展示界面
 */
class SearchShowActivity : LBaseActivity(), SearchShowContract.View {


    val mAdapter by lazy { createAdapter() }
    var page: Int = 1

    val presenter by lazy { SearchShowPresenter(this, this) }

    val keyword by lazy { intent.getStringExtra(com.pisces.android.wuha.Constant.KEY_CURRENT) }

    override fun onResponse(data: ArrayList<ServiceProvider>) {
        if (page == Config.page) {
            mAdapter.refresh(data)
        } else {
            mAdapter.addRange(data)
        }
    }

    override fun showEmpty(empty: Boolean) {
        Log.d("showEmpty", empty.toString())
        empty_layout.visibility = if (empty) View.VISIBLE else View.GONE
        recycler_view.visibility = if (empty) View.GONE else View.VISIBLE
    }

    override fun onLoadCompleted(page: Int, empty: Boolean) {
        if (page == Config.page) {
            recycler_view.refreshComplete()
            recycler_view.setNoMore(false)
        } else {
            recycler_view.loadMoreComplete()
            recycler_view.setNoMore(empty)
        }
    }

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


    companion object {
        fun start(context: Context, keyword: String) {
            val stater = Intent(context, SearchShowActivity::class.java)
            stater.putExtra(com.pisces.android.wuha.Constant.KEY_CURRENT, keyword)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_show_a)
        btnClose.setOnClickListener { finish() }

        edSearchView.text = keyword
        search_layout.setOnClickListener {
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

        loadData()
    }


    private fun loadData() {
        val bodySearch = BodySearch(Constant.getGpsY(), Constant.getGpsX(), keyword, page, Config.pageSize)
        presenter.loadList(bodySearch)
    }
}