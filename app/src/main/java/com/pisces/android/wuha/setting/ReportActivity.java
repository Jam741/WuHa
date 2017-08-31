package com.pisces.android.wuha.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;

/**
 * Created by Chris Li on 2017/9/1.
 * 举报投诉界面
 */

public class ReportActivity extends LBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setToolbarTitle("举报投诉");
    }
}
