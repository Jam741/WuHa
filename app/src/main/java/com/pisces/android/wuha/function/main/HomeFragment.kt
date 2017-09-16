package com.pisces.android.wuha.function.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.location.AMapLocation
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.framworkerlibrary.net.converter.GsonConverterFactory
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.locationlibrary.GDLocationUtil
import com.pisces.android.locationlibrary.LocationService
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.entity.BodyForServiceByCount
import com.pisces.android.wuha.entity.BodyForServiceByDistance
import com.pisces.android.wuha.entity.BodyForServiceByPrice

import com.pisces.android.wuha.function.home.medical.MedicalFragment
import com.pisces.android.wuha.function.home.service.ServiceFragment
import com.pisces.android.wuha.function.search.SearchForActivity
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.ApiService
import kotlinx.android.synthetic.main.home_frag.*
import kotlinx.android.synthetic.main.placeholder_frag.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Jam on 2017/8/24.
 * 主页fragment
 */
class HomeFragment : JBaseFragment() {
    private var mLocation: String = ""
    private val tabs by lazy { arrayListOf("医疗", "服务") }
    private val fragments by lazy { arrayListOf(MedicalFragment(), ServiceFragment()) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_home.run { adapter = TabAdapter(childFragmentManager, fragments as ArrayList<JBaseFragment>, tabs) }
        tab_home.run {
            setupWithViewPager(vp_home)
        }
        search_layout.setOnClickListener {
            SearchForActivity.start(context)
        }
//        search_layout_appbar.setOnClickListener { SearchForActivity.start(context) }


        initLocation()
        tx_location.setOnClickListener {
            initLocation()
        }
        activity.startService(Intent(activity, LocationService::class.java))

    }

    private fun initLocation() {

        GDLocationUtil.getCurrentLocation(object : GDLocationUtil.MyLocationListener {
            override fun result(location: AMapLocation?) {
                if (location != null) {
                    mLocation = location.address.toString()
                    Log.i("lyx", Constant.getLocation())
                    tx_location.text = Constant.getLocation()
                }
            }

            override fun fail(message: String?) {
                tx_location.text = "定位失败点击重新定位"
            }
        })


    }

}