package com.pisces.android.framworkerlibrary

import android.app.Application
import android.content.Context
import android.content.res.Resources

/**
 * Created by Jam on 2017/8/21.
 */

open class JBaseApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        sAppContent = applicationContext
    }

    companion object {

        var sAppContent: Context? = null

        fun appContext(): Context {
            return sAppContent!!
        }

        fun appResources(): Resources {
            return appContext().resources
        }
    }
}
