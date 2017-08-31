package com.pisces.android.wuha.pages;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pisces.android.framworkerlibrary.core.LBaseFragment;
import com.pisces.android.wuha.R;
import com.pisces.android.wuha.collect.CollectActivity;
import com.pisces.android.wuha.mine.CallActivity;
import com.pisces.android.wuha.mine.MessageActivity;
import com.pisces.android.wuha.mine.ShareActivity;
import com.pisces.android.wuha.setting.SettingActivity;

/**
 * Created by Chris Li on 2017/8/31.
 * 我的界面
 */

public class PersonageFragment extends LBaseFragment implements View.OnClickListener {
    private ImageView mSet;
    private LinearLayout mCollect;
    private LinearLayout mMessage;
    private LinearLayout mCall;
    private LinearLayout mShare;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_personage, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        transparencyBar(getActivity());
        initView();
    }

    private void initView() {
        mSet = (ImageView) getActivity().findViewById(R.id.f_personage_top_setting);
        mSet.setOnClickListener(this);
        mCollect = (LinearLayout) getActivity().findViewById(R.id.f_personage_center_collect);
        mCollect.setOnClickListener(this);
        mMessage = (LinearLayout) getActivity().findViewById(R.id.f_personage_center_message);
        mMessage.setOnClickListener(this);
        mCall = (LinearLayout) getActivity().findViewById(R.id.f_personage_center_call);
        mCall.setOnClickListener(this);
        mShare = (LinearLayout) getActivity().findViewById(R.id.f_personage_center_share);
        mShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_personage_top_setting://设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.f_personage_center_collect://我的收藏
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.f_personage_center_message://意见建议
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.f_personage_center_call://联系我们
                startActivity(new Intent(getActivity(), CallActivity.class));
                break;
            case R.id.f_personage_center_share://分享app
                startActivity(new Intent(getActivity(), ShareActivity.class));
                break;
        }

    }
}
