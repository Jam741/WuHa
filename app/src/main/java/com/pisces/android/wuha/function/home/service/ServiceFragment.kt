package com.pisces.android.wuha.function.home.service

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.home.BaseServiceProviderFragment
import com.pisces.android.wuha.function.home.medical.MedicalAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.f_service.*

import java.util.ArrayList


/**
 * Created by Chris Li on 2017/9/1.
 * 服务Fragment
 */

class ServiceFragment : BaseServiceProviderFragment() {


    override fun serviceProviderType(): Int {
        return 2
    }

    override fun createAdapter(): CommonRecyclerAdapter<ServiceProvider> {
        return object : CommonRecyclerAdapter<ServiceProvider>(this, null, R.layout.i_medical) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceProvider, position: Int) {
                holder.run {
                    setTextWith(R.id.i_medical_name, t.name)

                }
            }
        }
    }

}
