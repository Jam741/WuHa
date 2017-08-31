package com.pisces.android.wuha.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;

/**
 * Created by Chris Li on 2017/9/1.
 */

public class AboutUsActivity extends LBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setToolbarTitle("关于我们");
    }
}
