package com.pisces.android.wuha.function.collect

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.wuha.R

import java.util.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏适配器
 */

class CollectAdapter(private val mData: ArrayList<String>, private val mContext: Context) : RecyclerView.Adapter<CollectAdapter.MyViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(mInflater.inflate(R.layout.i_collect, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
