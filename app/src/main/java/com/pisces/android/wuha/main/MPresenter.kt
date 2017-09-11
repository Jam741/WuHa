package com.pisces.android.wuha.main

import android.app.Activity
import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.wuha.R
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder

/**
 * Created by Chris Li on 2017/9/3.
 */
class MPresenter(val activity: Activity) :JBasePresenter {

    val adapter by lazy { cretaeAdapter() }

    private fun cretaeAdapter(): CommonRecyclerAdapter<String> {
        return object : CommonRecyclerAdapter<String>(activity ,null, R.layout.i_medical){
            override fun convert(holder: RecyclerViewHolder, t: String, position: Int) {
                holder.run {
                    setTextWith(R.id.i_medical_distance,t)
                }
            }
        }
    }
}