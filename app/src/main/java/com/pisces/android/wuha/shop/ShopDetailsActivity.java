package com.pisces.android.wuha.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.framworkerlibrary.core.LBaseFragment;
import com.pisces.android.wuha.R;
import com.pisces.android.wuha.adapter.lPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/9/1.
 * 商品详情界面
 */

public class ShopDetailsActivity extends LBaseActivity implements View.OnClickListener {
    private ImageView mReturn;
    private TabLayout mTab;
    private ViewPager mViewPager;

    private ArrayList<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mViewList = new ArrayList<>();

    private lPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        initView();
    }

    private void initView() {
        mReturn = (ImageView) findViewById(R.id.a_shop_return);
        mReturn.setOnClickListener(this);
        mTab = (TabLayout) findViewById(R.id.a_shop_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.a_shop_view_pager);
        mTabList.add("服务列表");
        mTabList.add("客户信息");
        mTab.addTab(mTab.newTab().setText(mTabList.get(0)), true);
        mTab.addTab(mTab.newTab().setText(mTabList.get(1)), false);
        mViewList.add(new ServiceListFragment());
        mViewList.add(new ClientMessageFragment());

        mAdapter = new lPagerAdapter(getSupportFragmentManager(), mTabList, mViewList);
        mViewPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View v) {
    }
}
