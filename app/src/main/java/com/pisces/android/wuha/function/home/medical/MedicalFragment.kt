package com.pisces.android.wuha.function.home.medical

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.framworkerlibrary.core.JBaseView
import com.pisces.android.wuha.R
import kotlinx.android.synthetic.main.f_medical.*


import java.util.ArrayList

/**
 * Created by Chris Li on 2017/9/1.
 * 医疗Fragment
 */

class MedicalFragment : JBaseFragment(), JBaseView {
    private val mData = ArrayList<String>()


    val presenter by lazy { MPresenter(activity) }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_medical, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        distance.setOnClickListener {
            distance.setTextColor(R.color.colorYellowC1)
            popularity.setTextColor(R.color.colorGrayC1)
            price.setTextColor(R.color.colorGrayC1)
        }
        popularity.setOnClickListener {
            distance.setTextColor(R.color.colorGrayC1)
            popularity.setTextColor(R.color.colorYellowC1)
            price.setTextColor(R.color.colorGrayC1)
        }
        price.setOnClickListener {
            distance.setTextColor(R.color.colorGrayC1)
            popularity.setTextColor(R.color.colorGrayC1)
            price.setTextColor(R.color.colorYellowC1)
        }
        for (i in 0..9) {
            mData.add("")
        }
        recycler_view.run {
            layoutManager = LinearLayoutManager(activity)
//            adapter = presenter.adapter
            adapter = MedicalAdapter(activity, mData)
        }
    }
}
