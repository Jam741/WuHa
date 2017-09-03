package com.pisces.android.wuha.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pisces.android.framworkerlibrary.core.JBaseFragment;
import com.pisces.android.wuha.R;

import java.util.ArrayList;


/**
 * Created by Chris Li on 2017/9/1.
 * 服务Fragment
 */

public class ServiceFragment extends JBaseFragment implements DialogInterface.OnClickListener{

    private TextView mDistance;
    private TextView mPopularity;
    private TextView mPrice;
    private XRecyclerView mRecyclerView;

    private MedicalAdapter mAdapter;
    private ArrayList<String> mData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    private void initView() {
//        mDistance = (TextView) getActivity().findViewById(R.id.f_medical_distance);
//        mPopularity = (TextView) getActivity().findViewById(R.id.f_medical_popularity);
//        mPrice = (TextView) getActivity().findViewById(R.id.f_medical_price);
        mRecyclerView = (XRecyclerView) getActivity().findViewById(R.id.f_service_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new MedicalAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.f_medical_distance:
//                mDistance.setTextColor(getResources().getColor(R.color.colorMainYellow));
//                mPopularity.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mPrice.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mAdapter.notifyDataSetChanged();
//                break;
//            case R.id.f_medical_popularity:
//                mDistance.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mPopularity.setTextColor(this.getResources().getColor(R.color.colorMainYellow));
//                mPrice.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mAdapter.notifyDataSetChanged();
//                break;
//            case R.id.f_medical_price:
//                mDistance.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mPopularity.setTextColor(getResources().getColor(R.color.colorMainBlack));
//                mPrice.setTextColor(this.getResources().getColor(R.color.colorMainYellow));
//                mAdapter.notifyDataSetChanged();
//
//                break;
//        }
//
//    }
}
