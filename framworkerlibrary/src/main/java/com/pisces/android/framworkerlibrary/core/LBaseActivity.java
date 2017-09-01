package com.pisces.android.framworkerlibrary.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pisces.android.framworkerlibrary.R;


/**
 * Created by Chris Li on 2017/8/31.
 * 基础Activity
 */

public class LBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

//    private Toolbar mToolbar;
//    private TextView mToobarName;
//
//    public void setToolbarTitle(String title) {
//        if (mToolbar == null) {
//            mToolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            mToolbar.setNavigationIcon(R.mipmap.home_return2);
//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//        if (mToobarName == null) {
//            mToobarName = (TextView) findViewById(R.id.toolbar_center_name);
//        }
//        mToobarName.setText(title);
//    }


    private ImageView mToolbar;
    private TextView mToolbarName;

    public void setToolbarTitle(String title) {

        if (mToolbar == null) {
            mToolbar = (ImageView) findViewById(R.id.toolbar);
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (mToolbarName == null) {
            mToolbarName = (TextView) findViewById(R.id.toolbar_center_name);
        }
        mToolbarName.setText(title);
    }
}
