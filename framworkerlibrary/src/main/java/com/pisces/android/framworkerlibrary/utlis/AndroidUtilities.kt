package com.yingwumeijia.commonlibrary.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle

/**
 * @author Jam
 * *         create at 2016/5/19 16:50
 */
object AndroidUtilities {

    fun hasICS(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
    }

    fun hasHoneycomb(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
    }

    fun hasHoneycombMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1
    }

    fun hasJellyBean(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
    }

    fun hasJellyBeanMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
    }

    fun hasLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * 获取AndroidManifest中配置的meta-data

     * @param context Context
     * *
     * @param key     String
     * *
     * @return String
     */
    fun getMetaData(context: Context?, key: String?): String? {
        var metaData: Bundle? = null
        var value: String? = null
        if (context == null || key == null) {
            return null
        }
        try {
            val ai = context.packageManager.getApplicationInfo(
                    context.packageName, PackageManager.GET_META_DATA)
            if (null != ai) {
                metaData = ai.metaData
            }
            if (null != metaData) {
                value = metaData.getString(key)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            // Nothing to do
        }

        return value
    }
}
