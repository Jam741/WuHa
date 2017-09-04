package com.pisces.android.wuha.pages

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.SearchForActivity
import com.pisces.android.wuha.main.MedicalFragment
import com.pisces.android.wuha.main.ServiceFragment
import kotlinx.android.synthetic.main.home_frag.*

/**
 * Created by Jam on 2017/8/24.
 * 主页fragment
 */
class HomeFragment : JBaseFragment() {


    private val tabs by lazy { arrayListOf("医疗", "服务") }
    private val fragments by lazy { arrayListOf(MedicalFragment(), ServiceFragment()) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_home.run { adapter = TabAdapter(childFragmentManager, fragments, tabs) }
        tab_home.run {
            setupWithViewPager(vp_home)
        }
        search_view.setOnClickListener {
            SearchForActivity.start(context)
        }
    }

}