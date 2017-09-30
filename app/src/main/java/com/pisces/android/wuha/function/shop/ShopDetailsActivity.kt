package com.pisces.android.wuha.function.shop

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.pisces.adnroid.ltaskpicture.LImg
import com.pisces.android.framworkerlibrary.utlis.CallUtils
import com.pisces.android.framworkerlibrary.widget.adapter.TabAdapter
import com.pisces.android.sharesdk.ShareBean
import com.pisces.android.sharesdk.ShareClient
import com.pisces.android.wuha.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.entity.BodyForServiceDetailById
import com.pisces.android.wuha.entity.bean.ServiceDetailProvider
import com.pisces.android.wuha.function.shop.bean.BodyAddViewingCount
import com.pisces.android.wuha.function.user.UserController
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import kotlinx.android.synthetic.main.activity_shop_details.*
import kotlinx.android.synthetic.main.map_show_d.view.*
import java.net.URISyntaxException


/**
 * Created by Chris Li on 2017/9/1.
 * 商铺详情界面
 */

class ShopDetailsActivity : LBaseActivity(), View.OnClickListener, ShopDetailsContract.View {

    override fun showMsg(msg: String) {
        toastWith(msg)
    }

    /**
     * 显示是否收藏
     */
    override fun showIsCollect(isCollect: Boolean) {
        this.isCollect = isCollect
        if (isCollect) {
            collect.setImageResource(R.mipmap.mine_icon_collect)
        } else {
            collect.setImageResource(R.mipmap.home_icon_collect)
        }
    }

    val presenter by lazy { ShopDetailsPresenter(this, this) }

    private val shareClient by lazy { ShareClient(this, ShareBean("呜哈", "呜哈，专业的宠物信息服务平台", "https://www.pisces91.com/", "http://owq0wloan.bkt.clouddn.com/logo.png")) }

    var isCollect: Boolean = false
    var phoneNmubder: String = ""
    var isBD: Boolean = false
    var isGD: Boolean = false
    var isTX: Boolean = false
    var mData: ServiceDetailProvider? = null

    private val userId by lazy { UserController.getUserInfoBean(this)!!.id }

    val id: String by lazy { intent.getStringExtra(Constant.KEY_CURRENT) }

    private val bodyForCollect by lazy { BodyForCollect(userId!!.toString(), id) }

    val serviceListFragment by lazy { ServiceListFragment() }

    val clientMessageFragment by lazy { ClientMessageFragment() }

    companion object {
        fun start(context: Context, id: String) {
            val intent = Intent(context, ShopDetailsActivity::class.java)
            intent.putExtra(Constant.KEY_CURRENT, id)
            context.startActivity(intent)
        }
    }

    private val mTabList by lazy { arrayListOf("服务列表", "客户信息") }
    private val mViewList by lazy { arrayListOf(serviceListFragment, clientMessageFragment) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        initView()
        initData()

        if (UserController.isLogined(this)) {
            presenter.checkCollectStatus(bodyForCollect)
        }

        /*添加或移除收藏*/
        collect.setOnClickListener {
            if (!UserController.passPrecondition(this)) return@setOnClickListener
            if (isCollect) presenter.removeCollect(bodyForCollect) else presenter.addCollect(bodyForCollect)
        }

        /*打电话*/
        call_phone.setOnClickListener { CallUtils.callPhoneDIAL(phoneNmubder, this) }

        /*去哪里*/
        shop_goto.setOnClickListener {


            isBD = isAvilible(this, "com.baidu.BaiduMap")
            isGD = isAvilible(this, "com.autonavi.minimap")
            isTX = isAvilible(this, "com.tencent.map")

            showMyDialog()
        }
        addViewingCount()

        btnShare.setOnClickListener {
            shareClient.launchSharePopWindow(findViewById(R.id.root_layout))
        }
    }

    private fun showMyDialog() {
        val view = View.inflate(this, R.layout.map_show_d, null)

        view.bd_location.run {
            text = if (isBD) {
                "百度地图"
            } else {
                "百度地图(未安装)"
            }
            setOnClickListener {
                if (isBD) {
                    try {
                        //                          intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                        intent = Intent.getIntent("intent://map/direction?destination=latlng:" + mData!!.serviceProviderAddress.latitude + "," + mData!!.serviceProviderAddress.longitude + "|name:" + mData!!.serviceProviderAddress.mainAddressLine + //终点
                                "&mode=driving&" + //导航路线方式
                                "region=北京" + //
                                "&src=慧医#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end")
                        context.startActivity(intent) //启动调用
                    } catch (e: URISyntaxException) {
                        Log.e("intent", e.message)
                    }
                } else {
                    goToWebPager()
                }
            }
        }
        view.gd_location.run {
            text = if (isGD) {
                "高德地图"
            } else {
                "高德地图(未安装)"
            }

            setOnClickListener {
                if (isGD) {
                    try {
                        intent = Intent.getIntent("androidamap://navi?sourceApplication=WUHA&poiname=我的目的地&lat=" + mData!!.serviceProviderAddress.latitude + "&lon=" + mData!!.serviceProviderAddress.longitude + "&dev=0")
                        context.startActivity(intent)
                    } catch (e: URISyntaxException) {
                        e.printStackTrace()
                    }

                } else {
                    goToWebPager()
                }
            }


        }
        view.tx_location.run {
            text = if (isTX) {
                "腾讯地图"
            } else {
                "腾讯地图(未安装)"
            }

            setOnClickListener {
                if (isTX) {
                    val uri = Uri.parse("http://apis.map.qq.com/uri/v1/routeplan?type=walk&tocoord=" + mData!!.serviceProviderAddress.latitude + "," + mData!!.serviceProviderAddress.longitude + "&policy=1&referer=myapp&coord_type=1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)

                } else {
                    goToWebPager()
                }
            }

        }

        AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(true)
                .setNegativeButton("取消", { dialog, _ ->
                    dialog.dismiss()
                })
                .show()

    }

    /**
     * 通过网页进行定位
     */
    private fun goToWebPager() {
        val intent = Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=+" + mData!!.serviceProviderAddress.latitude + "," + mData!!.serviceProviderAddress.longitude + "(" + mData!!.serviceProviderAddress.mainAddressLine + ")"))
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        startActivity(intent)
    }


    /**
     * 添加浏览记录
     */
    private fun addViewingCount() {
        HttpUtli.toSubscribe(Api.service.addViewingCountForServiceProvider(BodyAddViewingCount(id, 1)), object : SimpleSubscriber<Any>(this) {
            override fun onSuccess(t: Any?) {
                if (t == null) return Unit
            }
        })


        /*分享按钮*/
        btnShare.setOnClickListener {
            shareClient.launchSharePopWindow(this.findViewById(R.id.root_layout))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        shareClient.didActivityResult(requestCode, resultCode, data)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        shareClient.didNewIntent(intent)

    }

    private fun initData() {
        HttpUtli.toSubscribe(Api.service.getServiceProviderDetail(BodyForServiceDetailById(id, com.pisces.android.locationlibrary.Constant.getGpsY(), com.pisces.android.locationlibrary.Constant.getGpsX())
        ), object : SimpleSubscriber<ServiceDetailProvider>(this) {
            override fun onSuccess(t: ServiceDetailProvider?) {
                if (t == null) return Unit
                Log.i("lyx", t.id)
                mData = t
                bindData(t)
                phoneNmubder = t.serviceProviderContact.phone
                serviceListFragment.setData(t.serviceProviderServiceCategories)
                clientMessageFragment.setData(t.serviceProviderIntroduction)
            }
        })
    }

    private fun bindData(t: ServiceDetailProvider) {
        var img: ImageView = findViewById(R.id.img_bg) as ImageView
        LImg.with(this).load(t.serviceProviderIntroduction.imagePath).into(img)
        name.text = t.name
        site.text = t.serviceProviderAddress.mainAddressLine
        person.text = t.viewingCount.toString() + "人去过"
        val dis: Int = t.distance.toInt()
        if (dis > 1000) {
            distance.text = (dis / 1000).toString() + "km"
        } else {
            distance.text = dis.toString() + "m"
        }
    }

    private fun initView() {
        shop_return!!.setOnClickListener(this)
        view_pager.run {
            adapter = TabAdapter(supportFragmentManager, mViewList, mTabList)
        }
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.shop_return -> {
                finish()
            }
        }
    }


    fun isAvilible(context: Context, packageName: String): Boolean {
        //获取packagemanager
        val packageManager = context.packageManager
        //获取所有已安装程序的包信息
        val packageInfos = packageManager.getInstalledPackages(0)
        //用于存储所有已安装程序的包名
        val packageNames = ArrayList<String>()
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (i in packageInfos.indices) {
                val packName = packageInfos[i].packageName
                packageNames.add(packName)
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName)
    }
}
