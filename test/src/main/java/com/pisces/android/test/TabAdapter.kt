package com.pisces.android.test

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Jam on 2017/8/22.
 */
class TabAdapter(fm:FragmentManager, private val fragments:ArrayList<Fragment>,private val titles:ArrayList<String>):FragmentStatePagerAdapter(fm) {
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