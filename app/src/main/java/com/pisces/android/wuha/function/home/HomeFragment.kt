package com.pisces.android.wuha.function.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amap.api.location.AMapLocation
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.locationlibrary.Constant
import com.pisces.android.locationlibrary.GDLocationUtil
import com.pisces.android.locationlibrary.LocationService
import com.pisces.android.wuha.R

import com.pisces.android.wuha.function.home.medical.MedicalFragment
import com.pisces.android.wuha.function.home.service.ServiceFragment
import com.pisces.android.wuha.function.search.SearchForActivity
import kotlinx.android.synthetic.main.home_frag.*

/**
 * Created by Jam on 2017/8/24.
 * 主页fragment
 */
class HomeFragment : JBaseFragment() {
    val locationBroadCastReceive by lazy { LocationBroadCastReceive() }
    private val tabs by lazy { arrayListOf("医疗", "服务") }
    private val fragments by lazy { arrayListOf(MedicalFragment(), ServiceFragment()) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerLocationBroadCastReceived()

        vp_home.run { adapter = TabAdapter(childFragmentManager, fragments as ArrayList<JBaseFragment>, tabs) }
        tab_home.run {
            setupWithViewPager(vp_home)
        }
        search_layout.setOnClickListener {
            SearchForActivity.start(context)
        }

        activity.startService(Intent(activity, LocationService::class.java))


    }

    /**
     * 注册广播
     */
    private fun registerLocationBroadCastReceived() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constant.LOCATION_BROADCAST_ACTION)
        context.registerReceiver(locationBroadCastReceive, intentFilter)
    }

    /**
     * 移除广播
     */
    private fun unRegisterLoginStatusBroadCastReceived() {
        context.unregisterReceiver(locationBroadCastReceive)
    }
    
    override fun onDestroy() {
        unRegisterLoginStatusBroadCastReceived()
        super.onDestroy()
    }


    inner class LocationBroadCastReceive : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            tx_location.text = intent.getStringExtra(Constant.C_LOCATION)
        }

    }

}