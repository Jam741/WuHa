package com.yingwumeijia.commonlibrary.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.pisces.android.framworkerlibrary.utlis.DeviceID

/**
 * @author Jam
 * *         create at 2016/5/19 17:43
 */
class AppInfo(context: Context) {

    var os: String? = null
    var deviceName: String? = null
    var deviceId: String? = null
    var version: String? = null
    var versionCode: String? = null
    var channel: String? = null
    var screenWidth: Int = 0
    var screenHeight: Int = 0

    init {
        initDeviceId(context)
        initVersion(context)
        initChannel(context)
        initOs()
        initDeviceName()
        initMetrics()
    }

    private fun initDeviceName() {
        this.deviceName = Build.DEVICE
    }

    private fun initOs() {
        this.os = Build.MODEL + "," + Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE
    }

    /**
     * 初始化尺度
     */
    private fun initMetrics() {
        this.screenWidth = ScreenUtils.screenWidth
        this.screenHeight = ScreenUtils.screenHeight
    }

    private fun initDeviceId(context: Context) {
        this.deviceId = DeviceID.getDeviceID(context)
    }

    private fun initVersion(context: Context) {
        val packageManager = context.packageManager
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        var version = ""
        var cdde = ""
        if (packageInfo != null) {
            version = packageInfo.versionName
            cdde = Integer.valueOf(packageInfo.versionCode)!!.toString()
        }

        this.version = version
        this.versionCode = cdde
    }

    private fun initChannel(context: Context) {
        this.channel = AndroidUtilities.getMetaData(context, "UMENG_CHANNEL")!!
    }
}
