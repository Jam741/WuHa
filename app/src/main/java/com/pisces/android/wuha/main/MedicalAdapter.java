package com.pisces.android.wuha.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pisces.android.wuha.R;
import com.pisces.android.wuha.shop.ShopDetailsActivity;

import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/9/1.
 * 医疗adapter
 */

class MedicalAdapter extends RecyclerView.Adapter<MedicalAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater mInflater;

    public MedicalAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.i_medical, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,ShopDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
