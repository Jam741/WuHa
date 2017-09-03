package com.pisces.android.wuha.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;
import com.pisces.android.wuha.adapter.lPagerAdapter;
import com.pisces.android.wuha.pages.HomeFragment;
import com.pisces.android.wuha.pages.PersonageFragment;

import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/8/31.
 */

public class HomeActivity extends LBaseActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private ArrayList<Fragment> mViewList = new ArrayList<>();
    private ArrayList<String> mTabList = new ArrayList<>();

    private lPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
    }

    private void initList() {
        mTabList.add("首页");
        mTabList.add("我的");
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(0)), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(1)), false);

        mViewList.add(new HomeFragment());
        mViewList.add(new PersonageFragment());

        mAdapter = new lPagerAdapter(getSupportFragmentManager(), mTabList, mViewList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPage);
        mTabLayout = (TabLayout) findViewById(R.id.tab);
    }


}
