package com.pisces.android.wuha.function.shop

import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.framworkerlibrary.core.JBaseView

/**
 * Created by Jam on 2017/9/30.
 */
interface ShopDetailsContract {

    interface View : JBaseView {
        fun showIsCollect(isCollect: Boolean)

        fun showMsg(msg:String)
    }

    interface Presenter : JBasePresenter {

        fun checkCollectStatus(bodyForCollect: BodyForCollect)

        fun addCollect(bodyForCollect: BodyForCollect)

        fun removeCollect(bodyForCollect: BodyForCollect)

    }
}