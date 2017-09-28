package com.pisces.android.wuha

import com.pisces.android.framworkerlibrary.JBaseApplication
import com.pisces.android.locationlibrary.GDLocationUtil
import com.pisces.android.sharesdk.ShareSDK

/**
 * Created by Jam on 2017/8/24.
 */
class App : JBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        GDLocationUtil.init(this)
        ShareSDK.initSDK(this)
    }

}