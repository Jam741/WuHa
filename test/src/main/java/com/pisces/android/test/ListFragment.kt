package com.pisces.android.test

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.list_frag.*

/**
 * Created by Jam on 2017/9/15.
 */
class ListFragment : JBaseFragment() {


    private val adapter by lazy { createSimpleAdapter() }

    private val datas by lazy { arrayListOf("11", "12", "13", "14", "11", "12", "13", "14", "11", "18") }

    private fun createSimpleAdapter(): CommonRecyclerAdapter<String> {
        return object : CommonRecyclerAdapter<String>(this, null, R.layout.simple_item) {
            override fun convert(holder: RecyclerViewHolder, t: String, position: Int) {
                holder.setTextWith(R.id.tv_content, t)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.list_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_content.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ListFragment.adapter
            setPullRefreshEnabled(false)
            setLoadingMoreEnabled(true)
            setLoadingListener(object : XRecyclerView.LoadingListener {
                override fun onRefresh() {

                }

                override fun onLoadMore() {
                    Log.d("JAM","onLoadMore")
                }
            })

        }

        adapter.refresh(datas)
    }
}