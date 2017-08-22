package com.pisces.android.framworkerlibrary.extra

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.R
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import kotlinx.android.synthetic.main.actionbar_layout.*
import kotlinx.android.synthetic.main.list_act.*

/**
 * Created by Jam on 2017/8/21.
 */
abstract open class JListActivity<T> : JBaseActivity() {

    private val adapter: CommonRecyclerAdapter<T> by lazy { createAdapter() }

    private val recyclerView by lazy { createRecyclerView() }

    abstract fun createRecyclerView(): RecyclerView

    abstract fun createAdapter(): CommonRecyclerAdapter<T>

    abstract fun getNavTitle(): String

    abstract fun initRecyclerView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_act)
        tvTopTitle.text = getNavTitle()

        contentView.addView(recyclerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.run { adapter = this@JListActivity.adapter }
        initRecyclerView()
    }

//    fun onRefresh() {}
//
//    fun onLoadMore() {}
}