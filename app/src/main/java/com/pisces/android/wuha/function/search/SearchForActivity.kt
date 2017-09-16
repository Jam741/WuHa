package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.search_frag.*

/**
 * Created by Chris Li on 2017/9/2.
 * 搜索界面
 */
class SearchForActivity : LBaseActivity() {
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
        }

        rv_interest.run {
            layoutManager = LinearLayoutManager(this@SearchForActivity)
            adapter = mInterestAdapter

        }

    }
}