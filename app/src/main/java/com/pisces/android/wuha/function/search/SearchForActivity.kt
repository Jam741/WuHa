package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.bean.HotSearch
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.search_frag.*
import kotlinx.android.synthetic.main.search_header.*

/**
 * Created by Chris Li on 2017/9/2.
 * 搜索界面
 */
class SearchForActivity : LBaseActivity() {
    val list: ArrayList<String> = ArrayList()
    val mHistoryAdapter by lazy { createHistoryAdapter() }

    val mInterestAdapter by lazy { createAdapter() }

    private fun createAdapter(): CommonRecyclerAdapter<HotSearch> {
        return object : CommonRecyclerAdapter<HotSearch>(this, null, R.layout.search_item_i) {
            override fun convert(holder: RecyclerViewHolder, t: HotSearch, position: Int) {
                holder.setTextWith(R.id.msg, t.keyword)

            }
        }
    }

    private fun createHistoryAdapter(): CommonRecyclerAdapter<String> {
        return object : CommonRecyclerAdapter<String>(this, null, R.layout.search_item_i) {
            override fun convert(holder: RecyclerViewHolder, t: String, position: Int) {
                holder.setTextWith(R.id.msg, t)
                holder.setOnItemClickListener(object : RecyclerViewHolder.OnItemCliceListener {
                    override fun itemClick(itemView: View, position: Int) {
                        SearchShowActivity.start(this@SearchForActivity, t)
                    }
                })
            }
        }
    }


    companion object {
        fun start(context: Context) {
            val stater = Intent(context, SearchForActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_frag)

        rv_history.run {
            layoutManager = LinearLayoutManager(this@SearchForActivity)
            adapter = mHistoryAdapter
            mHistoryAdapter

            mHistoryAdapter.refresh(list)
        }
        if (list.size == 0) {
            history_name.visibility = View.GONE
        } else {
            history_name.visibility = View.VISIBLE
        }

        rv_interest.run {
            layoutManager = LinearLayoutManager(this@SearchForActivity)
            adapter = mInterestAdapter

            HttpUtli.toSubscribe(Api.service.getHotSearch(), object : SimpleSubscriber<ArrayList<HotSearch>>(this@SearchForActivity) {
                override fun onSuccess(t: ArrayList<HotSearch>?) {
                    if (t == null) return Unit
                    mInterestAdapter.refresh(t)
                }
            })
        }

        btnSearch.setOnClickListener {
            SearchShowActivity.start(this, "")
        }

    }
}