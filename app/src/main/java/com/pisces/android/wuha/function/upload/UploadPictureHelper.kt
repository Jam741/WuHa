package com.pisces.android.wuha.function.upload

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.pisces.android.framworkerlibrary.utlis.ImageTools
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.qiniu.android.storage.UploadManager
import com.yingwumeijia.commonlibrary.utils.ListUtil
import org.json.JSONObject
import rx.Observable
import rx.schedulers.Schedulers
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
/**
 * Created by Chris Li on 2017/9/29.
 */
object UploadPictureHelper {


    fun uploadSinglePicture(context: Context, filePath: String, singleLoadListener: OnSingleLoadListener) {
        HttpUtli.toSubscribe(Api.service.getUpLoadToken(),object :ProgressSubscriber<String>(context){
            override fun onSuccess(t: String?) {
                UploadManager().put(File(filePath), null, t!!, { key, info, response -> singleLoadListener.success(assmibleUrl(response, filePath)) }, null)
            }

        })

    }


    fun upLoadMultinPicture(context: Context, filePaths: ArrayList<String>, multinLoadListener: OnMultinLoadListener) {
        if (ListUtil.isEmpty(filePaths)) {
            multinLoadListener.success(null)
            return Unit
        }

        var token: String? = null

        HttpUtli.toSubscribe( Api.service.getUpLoadToken()
                .flatMap { s ->
                    token = s
                    zooImages(filePaths)
                },
                object :ProgressSubscriber<ArrayList<Bitmap>>(context) {
                    override fun onSuccess(bitmaps: ArrayList<Bitmap>?) {
                        if (ListUtil.isEmpty(bitmaps)) {
                            Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show()
                        } else {
                            upLoadMultinPictureToQiniu(0, token!!, bitmaps!!, filePaths, multinLoadListener, ArrayList())
                        }
                    }
                })


    }

    private fun upLoadMultinPictureToQiniu(position: Int, token: String, bitmaps: ArrayList<Bitmap>, filePaths: ArrayList<String>, multinLoadListener: OnMultinLoadListener, urls: ArrayList<String>) {
        var index = position
        UploadManager().put(Bitmap2Bytes(bitmaps[index]), null, token, { key, info, response ->
            urls.add(assmibleUrl(response, filePaths[index]))
            index++
            if (index == bitmaps.size) multinLoadListener.success(urls)
            else upLoadMultinPictureToQiniu(index, token, bitmaps, filePaths, multinLoadListener, urls)
        }, null)
    }


    /**
     * 批量压缩
     */
    private fun zooImages(filePaths: ArrayList<String>): Observable<ArrayList<Bitmap>> {
        return Observable
                .just(filePaths)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap { strings -> bitmapsFromFilePaths(strings) }
                .map { bitmaps -> bitmaps.map { it -> comp(it) } as ArrayList<Bitmap> }
    }

    /**
     * 把Bitmap转Byte
     */
    fun Bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }


    private fun bitmapsFromFilePaths(filePaths: ArrayList<String>): Observable<ArrayList<Bitmap>> {
        return Observable.create<ArrayList<Bitmap>> { subcribe -> subcribe.onNext(filePaths.map { it -> getImageFromFilePath(it) } as ArrayList<Bitmap>) }
    }


    private fun assmibleUrl(res: JSONObject, filePath: String): String {
        return Config.BASE_QINIU_URL + res.getString("key") + ImageTools.getImageWidthHeightStringParam(filePath)
    }


    private fun getImageFromFilePath(srcPath: String): Bitmap {
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts)//此时返回bm为空

        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        val hh = 800f//这里设置高度为800f
        val ww = 480f//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / hh).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        return compressImage(bitmap)//压缩好比例大小后再进行质量压缩
    }

    private fun comp(image: Bitmap): Bitmap {

        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        if (baos.toByteArray().size / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset()//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos)//这里压缩50%，把压缩后的数据存放到baos中
        }
        var isBm = ByteArrayInputStream(baos.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        val hh = 800f//这里设置高度为800f
        val ww = 480f//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / hh).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565//降低图片从ARGB888到RGB565
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = ByteArrayInputStream(baos.toByteArray())
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        return compressImage(bitmap)//压缩好比例大小后再进行质量压缩
    }


    private fun compressImage(image: Bitmap): Bitmap {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            options -= 10//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中

        }
        val isBm = ByteArrayInputStream(baos.toByteArray())//把压缩后的数据baos存放到ByteArrayInputStream中
        val bitmap = BitmapFactory.decodeStream(isBm, null, null)//把ByteArrayInputStream数据生成图片
        return bitmap
    }


    interface OnSingleLoadListener {
        /**
         * @param url 上传成功的图片的地址
         */
        fun success(url: String)

    }

    interface OnMultinLoadListener {

        /**
         * @param urls 上传成功的图片的地址列表
         */
        fun success(urls: ArrayList<String>?)
    }
}