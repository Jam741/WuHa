package com.pisces.android.wuha.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pisces.android.framworkerlibrary.core.LBaseActivity;
import com.pisces.android.wuha.R;

/**
 * Created by Chris Li on 2017/8/31.
 */

public class SettingActivity extends LBaseActivity implements View.OnClickListener {
    private TextView mAboutUs;  //关于我们
    private TextView mGrade;    //给我评分
    private TextView mVersion;  //版本
    private TextView mReport;   //举报投诉
    private Button mReturn;     //退出

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setToolbarTitle("设置");
        initView();
    }

    private void initView() {
        mAboutUs = (TextView) findViewById(R.id.a_setting_about_us);
        mAboutUs.setOnClickListener(this);
        mGrade = (TextView) findViewById(R.id.a_setting_grade);
        mGrade.setOnClickListener(this);
        mVersion = (TextView) findViewById(R.id.a_setting_version);
        mVersion.setOnClickListener(this);
        mReport = (TextView) findViewById(R.id.a_setting_report);
        mReport.setOnClickListener(this);
        mReturn = (Button) findViewById(R.id.a_setting_return);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_setting_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.a_setting_grade:
                startActivity(new Intent(this, GradeActivity.class));
                break;
            case R.id.a_setting_version:
                startActivity(new Intent(this, VersionActivity.class));
                break;
            case R.id.a_setting_report:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.a_setting_return:
                Toast.makeText(this, "点击了退出", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
