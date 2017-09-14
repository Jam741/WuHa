package com.pisces.android.wuha.function.home.medical

import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.home.BaseServiceProviderFragment
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
    return object :CommonRecyclerAdapter<ServiceProvider>(this,null,R.layout.i_medical){
        override fun convert(holder: RecyclerViewHolder, t: ServiceProvider, position: Int) {
                holder.run {
                    setTextWith(R.id.i_medical_name,t.name)

                }
        }
    }
}


//class MedicalFragment : JBaseFragment() {
//
//    private val mData = ArrayList<String>()
//
//
//    val presenter by lazy { MPresenter(activity) }
//
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater!!.inflate(R.layout.f_medical, container, false)
//    }
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initView()
//    }
//
//    private fun initView() {
//        getLocation()
//
//        distance.setOnClickListener {
//            Toast.makeText(context, "距离", Toast.LENGTH_SHORT).show()
////            HttpUtli.toSubscribe(Api.service.getServiceFromLocation(BodyForServiceByDistance(0, 1, 10)))
//            HttpUtli.toSubscribe(Api.service.getServiceFromCount(BodyForServiceByCount(1, 1, 10)), object : ProgressSubscriber<ServiceProviderResponse>(context) {
//                override fun onSuccess(t: ServiceProviderResponse?) {
//                    if (t != null) {
//                        Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
//
//
//            distance.setTextColor(R.color.colorYellowC1)
//            popularity.setTextColor(R.color.colorGrayC1)
//            price.setTextColor(R.color.colorGrayC1)
//        }
//        popularity.setOnClickListener {
//            Toast.makeText(context, "人气", Toast.LENGTH_SHORT).show()
//            distance.setTextColor(R.color.colorGrayC1)
//            popularity.setTextColor(R.color.colorYellowC1)
//            price.setTextColor(R.color.colorGrayC1)
//        }
//        price.setOnClickListener {
//            Toast.makeText(context, "价格", Toast.LENGTH_SHORT).show()
//            distance.setTextColor(R.color.colorGrayC1)
//            popularity.setTextColor(R.color.colorGrayC1)
//            price.setTextColor(R.color.colorYellowC1)
//        }
//        for (i in 0..9) {
//            mData.add("")
//        }
//        recycler_view.run {
//            layoutManager = LinearLayoutManager(activity)
////            adapter = presenter.adapter
//            adapter = MedicalAdapter(activity, mData)
//        }
//    }
//
//    private fun getLocation() {
//        GDLocationUtil.getLocation(object : GDLocationUtil.MyLocationListener {
//            override fun result(location: AMapLocation?) {
//            }
//
//            override fun fail(message: String?) {
//            }
//
//        })
//    }
}
