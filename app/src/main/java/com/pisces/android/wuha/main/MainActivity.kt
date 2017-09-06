package com.pisces.android.wuha.main

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import com.orhanobut.logger.Logger

import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.wuha.PlaceHolderFragment
import com.pisces.android.wuha.R
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.pages.HomeFragment
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.CommonRecyclerAdapter
import com.yingwumeijia.commonlibrary.utils.adapter.recyclerview.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody

class MainActivity : JBaseActivity() {




//
//    private val tabs by lazy { arrayListOf("主页", "我的") }
//    private val fragments by lazy { arrayListOf(HomeFragment(), PlaceHolderFragment.newInstance("我的")) }
//


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

//        viewPage.run {
//            adapter = TabAdapter(supportFragmentManager, fragments, tabs)
//        }



    }


  public  data class Test(var ServiceProviderType:Int,var  CurrentLatitude:Float,var CurrentLongitude:Float,var CurrentPageIndex:Int,var NumberOfResultsPerPage:Int)
}
