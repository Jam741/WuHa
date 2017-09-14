package com.pisces.android.wuha.function.collect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.ServiceProvider

import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import kotlinx.android.synthetic.main.activity_collect.*
import java.util.*

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏界面
 */

class CollectActivity : LBaseActivity() {

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, CollectActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var mData: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
//        setToolbarTitle("我的收藏")
        initView()

    }

    private fun initView() {
//        HttpUtli.toSubscribe(Api.service.getUserFavorites(BodyCollect("1")), object : ProgressSubscriber<ServiceProvider>(this) {
//            override fun onSuccess(t: ServiceProvider?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
        mData = arrayListOf()
        for (i in 0..9) {
            mData!!.add("")
        }

        recycler_view.run {
            layoutManager = LinearLayoutManager(this@CollectActivity)
            adapter = CollectAdapter(mData!!, this@CollectActivity)
        }


    }
}
