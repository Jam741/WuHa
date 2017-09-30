package com.pisces.android.wuha.function.collect

import android.content.Context
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.yingwumeijia.commonlibrary.utils.ListUtil

/**
 * Created by Jam on 2017/9/30.
 */
class CollectPresenter(val context: Context, val view: CollectContract.View) : CollectContract.Presenter {
    override fun getCollectList(bodyCollect: BodyCollect) {
        HttpUtli.toSubscribe(Api.service.getUserFavorites(bodyCollect), object : ProgressSubscriber<ArrayList<ServiceProvider>>(context) {
            override fun onSuccess(t: ArrayList<ServiceProvider>?) {
                view.showEmpty(ListUtil.isEmpty(t))
                if (!ListUtil.isEmpty(t))
                    view.onResponse(t!!)
            }

        })
    }
}