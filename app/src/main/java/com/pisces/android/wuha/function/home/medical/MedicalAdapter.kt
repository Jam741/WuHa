package com.pisces.android.wuha.function.home.medical

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.shop.ShopDetailsActivity

import java.util.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 医疗adapter
 */

internal class MedicalAdapter(private val mContext: Context, private val mData: ArrayList<String>) : RecyclerView.Adapter<MedicalAdapter.MyViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(mInflater.inflate(R.layout.i_medical, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            ShopDetailsActivity.start(mContext)
        }

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
