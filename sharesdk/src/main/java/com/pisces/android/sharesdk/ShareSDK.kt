package com.pisces.android.sharesdk

import android.content.Context
import com.sina.weibo.sdk.constant.WBConstants.Base.APP_KEY
import com.sina.weibo.sdk.auth.AuthInfo
import com.sina.weibo.sdk.WbSdk


/**
 * Created by Jam on 2017/9/23.
 */
class ShareSDK {

    companion object {
        fun initSDK(context: Context) {
            WbSdk.install(context, AuthInfo(context, Config.WeiboConstants.APP_KEY, Config.WeiboConstants.REDIRECT_URL,
                    Config.WeiboConstants.SCOPE))
        }
    }

}