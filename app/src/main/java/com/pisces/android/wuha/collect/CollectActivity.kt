package com.pisces.android.wuha.collect

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.activity_personage.*

import java.util.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏界面
 */

class CollectActivity : JBaseActivity() {

    private var mData: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        setToolbarTitle("我的收藏")
        initView()

    }

    private fun initView() {
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
