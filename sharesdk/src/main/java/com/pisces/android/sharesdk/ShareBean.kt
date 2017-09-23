package com.pisces.android.sharesdk

import com.sina.weibo.sdk.share.WbShareCallback
import com.tencent.tauth.IUiListener

/**
 * Created by Jam on 2017/9/22.
 */
class ShareBean(val title: String, val summary: String, val targetUrl: String, val imageUrl: String) {

    val appName = "呜哈"

    //qq分享回调接口
    var qqUiListener: IUiListener? = null

    //微博分享回调接口
    val wbShareCallback: WbShareCallback? = null


}