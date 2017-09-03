package com.pisces.android.framworkerlibrary.widget.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.framworkerlibrary.core.LBaseFragment

/**
 * Created by Jam on 2017/8/22.
 */
class TabAdapter(fm: FragmentManager, private val fragments: ArrayList<JBaseFragment>, private val titles:ArrayList<String>): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
       return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}