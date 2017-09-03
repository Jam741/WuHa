package com.pisces.android.wuha.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;

/**
 * Created by Chris Li on 2017/9/1.
 * 联系我们界面
 */

public class CallActivity extends LBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        setToolbarTitle("联系我们");
    }
}
