package com.pisces.android.sharesdk

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.sina.weibo.sdk.api.ImageObject
import com.sina.weibo.sdk.api.TextObject
import com.sina.weibo.sdk.api.WeiboMultiMessage
import com.sina.weibo.sdk.share.WbShareHandler
import com.tencent.connect.share.QQShare
import com.tencent.mm.sdk.modelmsg.SendMessageToWX
import com.tencent.mm.sdk.modelmsg.WXMediaMessage
import com.tencent.mm.sdk.modelmsg.WXWebpageObject
import com.tencent.mm.sdk.openapi.IWXAPI
import com.tencent.mm.sdk.openapi.WXAPIFactory
import com.tencent.tauth.Tencent
import java.io.IOException
import java.net.URL


/**
 * Created by Jam on 2017/9/23.
 *
 * 分享SDK 操作类
 */

class ShareClient(val activity: Activity, val shareBean: ShareBean) {


    val WX_APP_ID by lazy { ShareSDK.getMetaData(activity, "WX_APP_ID") }

    val QQ_APP_ID = ShareSDK.getMetaData(activity, "QQ_APP_ID")

    private val tencent by lazy { Tencent.createInstance(QQ_APP_ID, activity.applicationContext) }

    private val wbShareHandler by lazy { WbShareHandler(activity).apply { registerApp() } }

    private val wxApi by lazy { createIWXAPI() }

    private fun createIWXAPI(): IWXAPI {
        Log.d("JAM", "WX_APP_ID" + WX_APP_ID)
        val api = WXAPIFactory.createWXAPI(activity, WX_APP_ID, true)
        api.registerApp(WX_APP_ID)
        return api
    }

    var isShowDialog = true

    private val shareEventLsitener by lazy {
        object : ShareEventListener {
            override fun toWxCricle() {
                shareToWx(shareBean, false)
                dismiss()
            }

            override fun toWxFirends() {
                shareToWx(shareBean, true)
                dismiss()
            }

            override fun toQQFirends() {
                shareToQQ(shareBean, 1)
                dismiss()
            }


            override fun toQQZone() {
                shareToQQ(shareBean, 2)
                dismiss()

            }

            override fun toWeibo() {
                shareToWeibo(shareBean)
                dismiss()
            }
        }
    }

    val shareDialog by lazy {
        ShareDialog(activity, shareEventLsitener)
    }


    val sharePopWindow by lazy {
        SharePopWindow(activity, shareEventLsitener)
    }


    fun launchShareDialog() {
        shareDialog.show()
        isShowDialog = true
    }

    fun launchSharePopWindow(v: View) {
        sharePopWindow.showAtLocation(v, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
        isShowDialog = false

    }


    fun dismiss() {
        if (isShowDialog)
            shareDialog.dismiss()
        else
            sharePopWindow.dismiss()
    }


    fun didActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Tencent.onActivityResultData(requestCode, resultCode, data, shareBean.qqUiListener)
    }


    fun didNewIntent(intent: Intent?) {
        wbShareHandler.doResultIntent(intent, shareBean.wbShareCallback)
    }


    fun shareToWx(isToFriends: Boolean) {
        shareToWx(shareBean, isToFriends)
    }

    fun shareToWx(shareBean: ShareBean, isToFriends: Boolean) {

        if (!wxApi.isWXAppInstalled) Toast.makeText(activity, "您尚未安装微信", Toast.LENGTH_SHORT).show()


        object : AsyncTask<String, Void, Bitmap>() {
            override fun doInBackground(vararg params: String?): Bitmap? {

                val url = URL(params[0])//这里输入图片地址
                var bitmap: Bitmap? = null
                try {
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    if (bitmap!!.byteCount > 4096000) {
                        val options = BitmapFactory.Options()
                        options.inSampleSize = bitmap.byteCount / 4096000//缩放比例
                        options.inJustDecodeBounds = false
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return bitmap

            }


            override fun onPostExecute(result: Bitmap?) {
                val req = SendMessageToWX.Req()
                val webpage = WXWebpageObject()
                webpage.webpageUrl = shareBean.targetUrl


                val msg = WXMediaMessage(webpage)
                msg.title = shareBean.title
                msg.description = shareBean.summary
                //这里替换一张自己工程里的图片资源
                msg.setThumbImage(result)

                req.transaction = System.currentTimeMillis().toString()
                req.message = msg

                req.scene = if (isToFriends) SendMessageToWX.Req.WXSceneSession else SendMessageToWX.Req.WXSceneTimeline

                req.scene = SendMessageToWX.Req.WXSceneSession
                wxApi.sendReq(req)

            }

        }.execute(shareBean.imageUrl)


    }

    /**
     * @param type = 1 分享到qq好友  type = 2 分享到QQ空间
     */
    fun shareToQQ(shareBean: ShareBean, type: Int) {
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT)
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareBean.title)
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareBean.summary)
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareBean.targetUrl)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareBean.imageUrl)
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareBean.appName)
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, if (type == 1) QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE else QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN)//分享时自动打开分享到QZone的对话框
        tencent.shareToQQ(activity, params, shareBean.qqUiListener)
    }

    fun shareToWeibo(shareBean: ShareBean) {
        val weiboMessage = WeiboMultiMessage()

        object : AsyncTask<String, Void, Bitmap>() {
            override fun doInBackground(vararg params: String?): Bitmap? {

                val url = URL(params[0])//这里输入图片地址
                var bitmap: Bitmap? = null
                try {
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    if (bitmap!!.byteCount > 4096000) {
                        val options = BitmapFactory.Options()
                        options.inSampleSize = bitmap.byteCount / 4096000//缩放比例
                        options.inJustDecodeBounds = false
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return bitmap

            }


            override fun onPostExecute(result: Bitmap?) {
                weiboMessage.textObject = getWbTextObj()
                weiboMessage.imageObject = getWbImageObj(result)
                wbShareHandler.shareMessage(weiboMessage, false)
            }

        }.execute(shareBean.imageUrl)

    }


    /**
     * 获取文本信息对象
     *
     * @return TextObject
     */
    private fun getWbTextObj(): TextObject {
        val textObject = TextObject()
        textObject.text = shareBean.title + "-" + shareBean.summary//这里输入文本
        return textObject
    }


    /**
     * 获取图片信息对象
     *
     * @return ImageObject
     */
    private fun getWbImageObj(bitmap: Bitmap?): ImageObject {
        val imageObject = ImageObject()
        imageObject.setImageObject(bitmap)
        return imageObject
    }
}
