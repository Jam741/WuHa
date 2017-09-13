package com.pisces.android.wuha.function.home

import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.framworkerlibrary.core.JBaseView
import com.pisces.android.wuha.entity.BodyForServiceByCount
import com.pisces.android.wuha.entity.BodyForServiceByDistance
import com.pisces.android.wuha.entity.BodyForServiceByPrice
import com.pisces.android.wuha.entity.bean.ServiceProviderResponse

/**
 * Created by Chris Li on 2017/9/12.
 */
interface BaseServiceProviderContract {

    interface View : JBaseView {

        fun onResponse(data: ArrayList<ServiceProviderResponse.ServiceProvider>)

        fun onLoadCompleted(page: Int, empty: Boolean)

        fun showEmpty(empty: Boolean)
    }


    interface Presenter : JBasePresenter {

        fun loadDataForBodyByDistance(bodyForServiceByDistance: BodyForServiceByDistance)
        fun loadDataForBodyByPrice(bodyForServiceByPrice: BodyForServiceByPrice)
        fun loadDataForBodyByCount(bodyForServiceByCount: BodyForServiceByCount)

    }
}