package com.pisces.android.wuha.function.search.show

import android.content.Context
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.search.BodySearch
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.ListUtil

/**
 * Created by Jam on 2017/9/17.
 */
class SearchShowPresenter(val context: Context, val view: SearchShowContract.View) : SearchShowContract.Presenter {
    override fun loadList(bodySearch: BodySearch) {
        HttpUtli.toSubscribe(Api.service.queryServiceProviderBySearceName(bodySearch), object : SimpleSubscriber<ArrayList<ServiceProvider>>(context) {
            override fun onSuccess(t: ArrayList<ServiceProvider>?) {
                view.showEmpty(bodySearch.CurrentPageIndex == Config.page && ListUtil.isEmpty(t))
                view.onLoadCompleted(bodySearch.CurrentPageIndex, ListUtil.isEmpty(t))
                if (!ListUtil.isEmpty(t))
                    view.onResponse(t!!)
            }
        })
    }


}