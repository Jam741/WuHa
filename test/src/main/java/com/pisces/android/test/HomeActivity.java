package com.pisces.android.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.pisces.adnroid.ltaskpicture.LImg;
import com.pisces.android.framworkerlibrary.core.JBaseActivity;

/**
 * Created by Chris Li on 2017/9/17.
 */

public class HomeActivity extends JBaseActivity {
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_a);
        mImg = (ImageView) findViewById(R.id.img);
        LImg.with(this).isCircle(true).load(R.mipmap.default_image).into(mImg);
    }
}
