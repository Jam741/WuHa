package com.pisces.android.wuha.function.search.show

import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.framworkerlibrary.core.JBaseView
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.search.BodySearch

/**
 * Created by Jam on 2017/9/17.
 */
interface SearchShowContract {

    interface View : JBaseView {


        fun onResponse(data: ArrayList<ServiceProvider>)

        fun showEmpty(empty: Boolean)

        fun onLoadCompleted(page: Int, empty: Boolean)
    }


    interface Presenter : JBasePresenter {

        fun loadList(bodySearch: BodySearch)
    }
}