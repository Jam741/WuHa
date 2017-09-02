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
import kotlinx.android.synthetic.main.f_service.*

import java.util.ArrayList


/**
 * Created by Chris Li on 2017/9/1.
 * 服务Fragment
 */

class ServiceFragment : JBaseFragment(),View.OnClickListener {
    override fun onClick(v: View?) {

    }

    private val mData = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_service, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        for (i in 0..9) {
            mData.add("")
        }
        recycler_view.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = MedicalAdapter(activity, mData)
        }

    }
}
