package com.pisces.android.wuha.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/8/31.
 */

public class lPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTabList;
    private ArrayList<Fragment> mViewList;

    public lPagerAdapter(FragmentManager fm, ArrayList<String> mTabList, ArrayList<Fragment> mViewList) {
        super(fm);
        this.mTabList = mTabList;
        this.mViewList = mViewList;
    }


    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }


    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }
}
