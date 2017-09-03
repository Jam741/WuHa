package com.pisces.android.wuha.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pisces.android.framworkerlibrary.core.LBaseFragment;
import com.pisces.android.wuha.R;

/**
 * Created by Chris Li on 2017/9/1.
 * 商家详情界面里面的商家信息界面
 */

public class ClientMessageFragment extends LBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_client_message, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
