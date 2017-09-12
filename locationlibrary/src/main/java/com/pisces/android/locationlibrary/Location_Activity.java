package com.pisces.android.locationlibrary;

import com.amap.api.location.AMapLocation;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Chris Li on 2017/9/12.
 * 定位界面
 */
public class Location_Activity extends CheckPermissionsActivity implements OnClickListener {
    private TextView tvResult;
    private Button btLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setTitle(R.string.title_location);
        initView();
    }

    //初始化控件
    private void initView() {
        tvResult = (TextView) findViewById(R.id.tv_result);
        btLocation = (Button) findViewById(R.id.bt_location);
        btLocation.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_location) {
            if (btLocation.getText().equals(getResources().getString(R.string.startLocation))) {
                btLocation.setText(getResources().getString(R.string.stopLocation));
                tvResult.setText("正在定位...");
                startLocation();
            } else {
                btLocation.setText(getResources().getString(
                        R.string.startLocation));
                stopLocation();
                tvResult.setText("定位停止");
            }
        }
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        /*测试用*/
        GDLocationUtil.getLocation(new GDLocationUtil.MyLocationListener() {
            @Override
            public void result(AMapLocation location) {
                tvResult.setText(location.getAoiName());
            }

            @Override
            public void fail(String message) {

            }
        });

        GDLocationUtil.getCurrentLocation(new GDLocationUtil.MyLocationListener() {
            @Override
            public void result(AMapLocation location) {
            }

            @Override
            public void fail(String message) {
            }
        });
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        // 停止定位
        GDLocationUtil.destroy();
    }


}
