package com.pisces.android.wuha.function.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.framworkerlibrary.core.JBaseFragment

import com.pisces.android.wuha.R
import kotlinx.android.synthetic.main.f_service.*

/**
 * Created by Chris Li on 2017/9/1.
 * 商家详情里面的服务列表界面
 */

class ServiceListFragment : JBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_service_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        recycler_view.run {

        }

    }
}
