package com.pisces.android.sharesdk

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import com.sina.weibo.sdk.constant.WBConstants.Base.APP_KEY
import com.sina.weibo.sdk.auth.AuthInfo
import com.sina.weibo.sdk.WbSdk


/**
 * Created by Jam on 2017/9/23.
 */
class ShareSDK {

    companion object {
        fun initSDK(context: Context) {
            WbSdk.install(context, AuthInfo(context, getMetaData(context, Config.KEY_WEIBO_APP_KEY), Config.WeiboConstants.REDIRECT_URL,
                    Config.WeiboConstants.SCOPE))
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

}