package com.yingwumeijia.commonlibrary.utils.adapter.recyclerview

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by jamisonline on 2017/5/31.
 */
abstract class CommonRecyclerAdapter<T>(var activity: Activity?, var fragment: Fragment?, var context: Context?, dataList: List<T>?, var layoutId: Int) : RecyclerView.Adapter<RecyclerViewHolder>() {

    var data: ArrayList<T>? = null

    init {
        data = if (dataList == null) null else dataList as ArrayList<T>
    }

    constructor(fragment: Fragment, data: List<T>?, layoutId: Int) : this(null, fragment, null, data, layoutId)

    constructor(activity: Activity, data: List<T>?, layoutId: Int) : this(activity, null, null, data, layoutId)

    constructor(context: Context, data: List<T>?, layoutId: Int) : this(null, null, context, data, layoutId)


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        return if (activity == null) {
            activity = fragment!!.activity
            RecyclerViewHolder.get(fragment!!, parent!!, layoutId, viewType)
        } else {
            RecyclerViewHolder.get(activity!!, parent!!, layoutId, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        holder!!.position = position
        convert(holder, data!![position], position)
    }

    abstract fun convert(holder: RecyclerViewHolder, t: T, position: Int)

    fun isLoastOneAt(position: Int): Boolean {
        return position == itemCount - 1
    }

    override fun getItemCount(): Int {
        if (data == null)
            return 0
        else
            return data!!.size
    }

    fun getItemAt(position: Int): T {
        return data!![position]
    }


    fun clearnData() {
        data!!.clear()
    }

    fun refresh(dataArray: ArrayList<T>) {
        if (data == null) {
            data = ArrayList<T>()
        }
        data!!.clear()
        data!!.addAll(dataArray)
        notifyDataSetChanged()
    }


    fun addRange(dataArray: ArrayList<T>) {
        if (data == null) {
            data = ArrayList<T>()
        }
        data!!.addAll(dataArray)
        notifyDataSetChanged()
    }

    fun remove(index: Int) {
        if (data == null) return
        data!!.removeAt(index)
        notifyItemRemoved(index)
    }

    fun insert(o: T) {
        if (data == null) return
        data!!.add(o)
        notifyItemInserted(data!!.size)
    }

    fun insert(index: Int, o: T) {
        if (data == null) return
        data!!.add(index, o)
        notifyItemInserted(index)
    }

}