package com.pisces.android.wuha.function.collect

import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.framworkerlibrary.core.JBaseView
import com.pisces.android.wuha.entity.bean.ServiceProvider

/**
 * Created by Jam on 2017/9/30.
 */
interface CollectContract {

    interface View : JBaseView {

        fun onResponse(data: ArrayList<ServiceProvider>)

        fun onLoadCompleted(page: Int, empty: Boolean)

        fun showEmpty(empty: Boolean)
    }

    interface Presenter : JBasePresenter {
        fun getCollectList(bodyCollect: BodyCollect)
    }
}