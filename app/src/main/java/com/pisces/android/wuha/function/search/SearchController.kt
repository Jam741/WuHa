package com.pisces.android.wuha.function.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.orhanobut.logger.Logger
import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.bean.HotSearch
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.ListUtil
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import rx.Observable
import rx.Subscriber
import rx.functions.Action1

/**
 * Created by jamisonline on 2017/9/17.
 */
class SearchController(val context: Context, val onLoadHistoryAndHotKeyWordsListener: OnLoadHistoryAndHotKeyWordsListener) {


    val hotKeyWordsAdapter by lazy {
        createKeyWordsAdapter()
    }


    val historyKeyWordsAdapter by lazy {
        createKeyWordsAdapter()
    }

    fun loadHistoryKeyWords() {
        val ob: Observable<List<String>> = Observable
                .create(Observable.OnSubscribe { t: Subscriber<in List<String>>? ->
                    Log.d("JHAM", "xxxxxxxxxxxxxxxx:" + SearchHistoryMenager.getHistory(context).size)
                    t!!.onNext(SearchHistoryMenager.getHistory(context))
                })
        ob.compose(HttpUtli.applySchedulers()).subscribe(object : Action1<List<String>> {
            override fun call(t: List<String>?) {
                Log.d("JHAM", "------:" + t!!.size)
                if (!ListUtil.isEmpty(t))
                    historyKeyWordsAdapter.refresh(t!!)
                onLoadHistoryAndHotKeyWordsListener.didLoadHistoryKeyWords(t)
            }
        })
    }


    fun loadHotKeyWords() {

        HttpUtli.toSubscribe(Api.service.getHotSearch(), object : SimpleSubscriber<ArrayList<HotSearch>>(context) {
            override fun onSuccess(t: ArrayList<HotSearch>?) {
                if (!ListUtil.isEmpty(t)) {
                    val hots = ArrayList<String>()
                    t!!.mapTo(hots) { it.keyword }
                    onLoadHistoryAndHotKeyWordsListener.didLoadHotKeyWords(hots)
                }
            }
        })
    }


    fun insertHistoryKeyWords(keyWords: String) {
        SearchHistoryMenager.insertHistory(context, keyWords)
        historyKeyWordsAdapter.refresh(SearchHistoryMenager.getHistory(context))
    }


    fun clearHistory(){
        SearchHistoryMenager.clearnHistory(context)
        loadHistoryKeyWords()
    }

    private fun createKeyWordsAdapter(): TagAdapter<String> {
        return object : TagAdapter<String>() {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val mInflater = LayoutInflater.from(context)
                val textView = mInflater.inflate(R.layout.tv_tag, parent, false) as TextView
                textView.text = t
                return textView
            }
        }
    }


    interface OnLoadHistoryAndHotKeyWordsListener {

        fun didLoadHistoryKeyWords(data: List<String>?)

        fun didLoadHotKeyWords(data: List<String>?)

    }

}