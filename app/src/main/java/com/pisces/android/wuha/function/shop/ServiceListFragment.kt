package com.pisces.android.wuha.function.shop

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.framworkerlibrary.core.JBaseFragment

import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.bean.ServiceDetailProvider
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder

import kotlinx.android.synthetic.main.f_service_list.*

/**
 * Created by Chris Li on 2017/9/1.
 * 商家详情里面的服务列表界面
 */

class ServiceListFragment : JBaseFragment() {

    val leftAdapter by lazy { createLeftAdapter() }
    val rightAdapter by lazy { createRightAdapter() }

    private fun createRightAdapter(): CommonRecyclerAdapter<ServiceDetailProvider.ServiceProviderServiceCategoriesBean.SubCategoriesBean> {
        return object : CommonRecyclerAdapter<ServiceDetailProvider.ServiceProviderServiceCategoriesBean.SubCategoriesBean>(this, null, R.layout.service_list_right_i) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceDetailProvider.ServiceProviderServiceCategoriesBean.SubCategoriesBean, position: Int) {
                holder.run {
                    setTextWith(R.id.name, t.name)
                    setTextWith(R.id.price, "￥" + t.price)
                }
            }

        }
    }


    private fun createLeftAdapter(): CommonRecyclerAdapter<ServiceDetailProvider.ServiceProviderServiceCategoriesBean> {

        var mPosition = 0

        return object : CommonRecyclerAdapter<ServiceDetailProvider.ServiceProviderServiceCategoriesBean>(this, null, R.layout.service_list_left_i) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceDetailProvider.ServiceProviderServiceCategoriesBean, position: Int) {
                holder.run {
                    setTextWith(R.id.i_name, t.name)
                    setOnItemClickListener(object : RecyclerViewHolder.OnItemCliceListener {
                        override fun itemClick(itemView: View, position: Int) {
                            mPosition = position
                            notifyDataSetChanged()
                            rightAdapter.refresh(t.subCategories)
                        }
                    })

                    if (mPosition == position) {
                        setBackgroundRes(R.id.i_name, R.color.colorGrayC2)
                        setTextColorRes(R.id.i_name, R.color.colorTextWhite)

                    } else {
                        setBackgroundRes(R.id.i_name, R.color.colorWhiteC1)
                        setTextColorRes(R.id.i_name, R.color.colorBlackC1)

                    }
                }


            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_service_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_left.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ServiceListFragment.leftAdapter
        }

        recycler_view_right.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = rightAdapter
        }
    }


    fun setData(mData: ArrayList<ServiceDetailProvider.ServiceProviderServiceCategoriesBean>) {
        leftAdapter.refresh(mData)
        rightAdapter.refresh(mData[0].subCategories)
    }
}
