package com.pisces.android.wuha.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.wuha.R
import kotlinx.android.synthetic.main.f_medical.*


import java.util.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 医疗Fragment
 */

class MedicalFragment : JBaseFragment(), View.OnClickListener {
    private val mData = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_medical, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        distance!!.setOnClickListener(this)
        popularity!!.setOnClickListener(this)
        price!!.setOnClickListener(this)

        for (i in 0..9) {
            mData.add("")
        }
        recycler_view.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = MedicalAdapter(activity, mData)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.distance -> {
                distance!!.setTextColor(resources.getColor(R.color.colorMainYellow))
                popularity!!.setTextColor(resources.getColor(R.color.colorMainA2))
                price!!.setTextColor(resources.getColor(R.color.colorMainA2))
                recycler_view!!.adapter!!.notifyDataSetChanged()
            }
            R.id.popularity -> {
                distance!!.setTextColor(resources.getColor(R.color.colorMainA2))
                popularity!!.setTextColor(this.resources.getColor(R.color.colorMainYellow))
                price!!.setTextColor(resources.getColor(R.color.colorMainA2))
                recycler_view!!.adapter!!.notifyDataSetChanged()
            }
            R.id.price -> {
                distance!!.setTextColor(resources.getColor(R.color.colorMainA2))
                popularity!!.setTextColor(resources.getColor(R.color.colorMainA2))
                price!!.setTextColor(this.resources.getColor(R.color.colorMainYellow))
                recycler_view!!.adapter!!.notifyDataSetChanged()
            }
        }

    }
}
