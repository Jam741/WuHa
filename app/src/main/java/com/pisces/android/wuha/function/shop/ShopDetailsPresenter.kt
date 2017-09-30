package com.pisces.android.wuha.function.shop

import android.content.Context
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber

/**
 * Created by Jam on 2017/9/30.
 */
class ShopDetailsPresenter(val context: Context, val view: ShopDetailsContract.View) : ShopDetailsContract.Presenter {
    override fun checkCollectStatus(bodyForCollect: BodyForCollect) {
        HttpUtli.toSubscribe(Api.service.checkCollectForServiceProvider(bodyForCollect), object : ProgressSubscriber<Int>(context) {
            override fun onSuccess(t: Int?) {
                view.showIsCollect(t!! == 1)
            }

        })
    }

    override fun addCollect(bodyForCollect: BodyForCollect) {
        HttpUtli.toSubscribe(Api.service.addUserFavorite(bodyForCollect), object : SimpleSubscriber<Any>(context) {
            override fun onSuccess(t: Any?) {
                view.showIsCollect(true)
                view.showMsg("该商铺加入收藏")
            }
        })
    }

    override fun removeCollect(bodyForCollect: BodyForCollect) {
        HttpUtli.toSubscribe(Api.service.cancelCollect(bodyForCollect), object : ProgressSubscriber<Int>(context) {
            override fun onSuccess(t: Int?) {
                view.showIsCollect(false)
                view.showMsg("该商铺移出收藏")
            }
        })
    }
}