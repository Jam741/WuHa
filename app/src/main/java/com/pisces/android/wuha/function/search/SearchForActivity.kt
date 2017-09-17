package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
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
    val mHistoryAdapter by lazy { createAdapter() }

    val mInterestAdapter by lazy { createAdapter() }

    private fun createAdapter(): CommonRecyclerAdapter<String> {
        return object : CommonRecyclerAdapter<String>(this, null, R.layout.search_item_i) {
            override fun convert(holder: RecyclerViewHolder, t: String, position: Int) {
                holder.setTextWith(R.id.msg, t)
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


            list.add("1")
            list.add("11")
            list.add("111")
            list.add("1111")

            mHistoryAdapter.refresh(list)
        }

        rv_interest.run {
            layoutManager = LinearLayoutManager(this@SearchForActivity)
            adapter = mInterestAdapter
            mInterestAdapter.refresh(list)

        }

        btnSearch.setOnClickListener{
            SearchShowActivity.start(this)
        }

    }
}