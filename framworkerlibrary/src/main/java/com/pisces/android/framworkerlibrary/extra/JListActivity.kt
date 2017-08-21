package com.pisces.android.framworkerlibrary.extra

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.framworkerlibrary.R
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import kotlinx.android.synthetic.main.actionbar_layout.*
import kotlinx.android.synthetic.main.list_act.*
import kotlinx.android.synthetic.main.list_layout.*

/**
 * Created by Jam on 2017/8/21.
 */
abstract open class JListActivity : JBaseActivity() {

    val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> by lazy { createAdapter() }

    val xrecyclerView by lazy { XRecyclerView(this) }

    val recyclerView by lazy { RecyclerView(this) }

    abstract fun createAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

    abstract fun getNavTitle(): String

    abstract fun neeLoadMore(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_act)
        tvTopTitle.text = getNavTitle()

        if (neeLoadMore()) {
            contentView.addView(xrecyclerView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            xrecyclerView.run {
                adapter = this@JListActivity.adapter
                setLoadingListener(object : XRecyclerView.LoadingListener {
                    override fun onRefresh() {
                        this@JListActivity.onRefresh()
                    }

                    override fun onLoadMore() {
                        this@JListActivity.onLoadMore()
                    }
                })
            }
        } else {
            contentView.addView(recyclerView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        }


    }

    fun onRefresh() {}

    fun onLoadMore() {}

//    fun emptyView():View
}