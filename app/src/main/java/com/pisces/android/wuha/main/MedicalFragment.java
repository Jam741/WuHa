package com.pisces.android.wuha.main;
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
 * 医疗Fragment
 */

public class MedicalFragment extends JBaseFragment implements View.OnClickListener {
    private TextView mDistance;
    private TextView mPopularity;
    private TextView mPrice;
    private XRecyclerView mRecyclerView;

    private MedicalAdapter mAdapter;
    private ArrayList<String> mData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_medical, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mDistance = (TextView) getActivity().findViewById(R.id.f_medical_distance);
        mDistance.setOnClickListener(this);
        mPopularity = (TextView) getActivity().findViewById(R.id.f_medical_popularity);
        mPopularity.setOnClickListener(this);
        mPrice = (TextView) getActivity().findViewById(R.id.f_medical_price);
        mPrice.setOnClickListener(this);
        mRecyclerView = (XRecyclerView) getActivity().findViewById(R.id.f_medical_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < 10; i++) {
            mData.add("");
        }
        mAdapter = new MedicalAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_medical_distance:
                mDistance.setTextColor(getResources().getColor(R.color.colorMainYellow));
                mPopularity.setTextColor(getResources().getColor(R.color.colorMainA2));
                mPrice.setTextColor(getResources().getColor(R.color.colorMainA2));
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.f_medical_popularity:
                mDistance.setTextColor(getResources().getColor(R.color.colorMainA2));
                mPopularity.setTextColor(this.getResources().getColor(R.color.colorMainYellow));
                mPrice.setTextColor(getResources().getColor(R.color.colorMainA2));
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.f_medical_price:
                mDistance.setTextColor(getResources().getColor(R.color.colorMainA2));
                mPopularity.setTextColor(getResources().getColor(R.color.colorMainA2));
                mPrice.setTextColor(this.getResources().getColor(R.color.colorMainYellow));
                mAdapter.notifyDataSetChanged();

                break;
        }

    }
}
