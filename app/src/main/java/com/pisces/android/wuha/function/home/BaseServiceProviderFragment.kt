package com.pisces.android.wuha.function.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.location.AMapLocation
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseFragment
import com.pisces.android.wuha.entity.BodyForServiceByCount
import com.pisces.android.wuha.entity.BodyForServiceByDistance
import com.pisces.android.wuha.entity.BodyForServiceByPrice
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.service_provider_frag.*
import kotlinx.android.synthetic.main.service_provider_header.*

/**
 * Created by Chris Li on 2017/9/12.
 */
abstract class BaseServiceProviderFragment : LBaseFragment(), BaseServiceProviderContract.View {


    var page: Int = Config.page
    var page_size: Int = Config.pageSize

    val presenter by lazy { ServiceProviderPresenter(this, context) }

    val adapter by lazy { createAdapter() }

    val type by lazy { serviceProviderType() }
//    val distance by lazy { serviceProviderDistance() }


    var way: Int = 1

//    abstract fun serviceProviderDistance(): AMapLocation//定位

    abstract fun serviceProviderType(): Int

    abstract fun createAdapter(): CommonRecyclerAdapter<ServiceProvider>

    override fun onResponse(data: ArrayList<ServiceProvider>) {
        if (page == Config.page) {
            adapter.refresh(data)
        } else {
            adapter.addRange(data)
        }
    }

    override fun onLoadCompleted(page: Int, empty: Boolean) {
        if (page == Config.page) {
            rv_content.refreshComplete()
            rv_content.setNoMore(false)
        } else {
            rv_content.loadMoreComplete()
            rv_content.setNoMore(empty)
        }
    }

    override fun showEmpty(empty: Boolean) {
        empty_layout.visibility = if (empty) View.VISIBLE else View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.service_provider_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_content.run {
            loadData()
            layoutManager = LinearLayoutManager(context)
            adapter = this@BaseServiceProviderFragment.adapter
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


        radio_group.run {
            check(R.id.rad_distance)
            setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.rad_distance -> {
                        way = 1
                        page = 1
                        loadData()
                    }
                    R.id.rad_rq -> {
                        page = 1
                        way = 2
                        loadData()
                    }

                    R.id.rad_price -> {
                        page = 1
                        way = 3
                        loadData()
                    }

                }

            }
        }

        loadData()
    }

    fun loadData() {
        when (way) {
            1 -> presenter.loadDataForBodyByDistance(BodyForServiceByDistance(type, Constant.getGpsX(), Constant.getGpsY(), page, page_size))
            2 -> presenter.loadDataForBodyByCount(BodyForServiceByCount(type, Constant.getGpsX(), Constant.getGpsY(), page, page_size))
            3 -> presenter.loadDataForBodyByPrice(BodyForServiceByPrice(type, Constant.getGpsX(), Constant.getGpsY(), page, page_size))
        }
    }

}