package com.pisces.android.wuha.collect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pisces.android.wuha.R;

import java.util.ArrayList;

/**
 * Created by Chris Li on 2017/9/1.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyViewHolder> {
    private ArrayList<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public CollectAdapter(ArrayList<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.i_collect, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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
