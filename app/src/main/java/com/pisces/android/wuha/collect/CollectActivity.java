package com.pisces.android.wuha.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;

import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/9/1.
 * 我的收藏界面
 */

public class CollectActivity extends LBaseActivity {
    private XRecyclerView mRecyclerView;
    private CollectAdapter mAdapter;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        setToolbarTitle("我的收藏");
        initView();

    }

    private void initView() {
        mRecyclerView = (XRecyclerView) findViewById(R.id.collect_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new CollectAdapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
