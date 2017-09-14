package com.pisces.android.wuha.function.home.medical

import android.view.View
import android.widget.RadioGroup
import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.home.BaseServiceProviderFragment
import com.pisces.android.wuha.function.shop.ShopDetailsActivity
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder


/**
 * Created by Chris Li on 2017/9/1.
 * 医疗Fragment
 */

class MedicalFragment : BaseServiceProviderFragment() {


    override fun serviceProviderType(): Int {
        return 1
    }

    override fun createAdapter(): CommonRecyclerAdapter<ServiceProvider> {
        return object : CommonRecyclerAdapter<ServiceProvider>(this, null, R.layout.i_medical) {
            override fun convert(holder: RecyclerViewHolder, t: ServiceProvider, position: Int) {
                holder.run {
                    setTextWith(R.id.i_medical_name, t.name)
                    setTextWith(R.id.i_medical_site, t.serviceProviderAddress.mainAddressLine)
                    setTextWith(R.id.i_medical_price, "￥" + t.startingPrice)
                    setTextWith(R.id.i_medical_person, t.viewingCount.toString() + "人去过")
                    var dis: Int = t.distance.toInt()
                    if (dis > 1000) {
                        setTextWith(R.id.i_medical_distance, (dis / 1000).toString() + "km")
                    } else {
                        setTextWith(R.id.i_medical_distance, dis.toString() + "m")
                    }
                    setImageUrl(R.id.i_medical_img, t.serviceProviderCertificationInfo.legalPersonInfoImagePath)
                }
                holder.setOnItemClickListener(object : RecyclerViewHolder.OnItemCliceListener {
                    override fun itemClick(itemView: View, position: Int) {
                        ShopDetailsActivity.start(getContext(), t.id)
                    }
                })
            }
        }
    }
}
