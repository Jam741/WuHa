package com.pisces.android.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.test.template.TitleFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Jam on 2017/8/22.
 */
class HomeFragment:JBaseFragment() {

    private val titles by lazy { arrayListOf("ONE","TWO","THREE","FOUR") }

    private val fragments by lazy { ArrayList<Fragment>().apply { titles.mapTo(this){TitleFragment.newInstance(it)} } }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_frag,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        page.adapter = TabAdapter(childFragmentManager,fragments,titles)
        tab.setupWithViewPager(page)
    }
}