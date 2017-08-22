package com.pisces.android.framworkerlibrary.extra

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.RecyclerView
import com.jcodecraeer.xrecyclerview.XRecyclerView

/**
 * Created by Jam on 2017/8/22.
 */
abstract open class JListStaticActivity<T>:JListActivity<T>() {
    override fun createRecyclerView(): RecyclerView {
        return XRecyclerView(this)
    }

    override fun initRecyclerView() {

    }

}