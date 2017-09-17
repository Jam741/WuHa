package com.pisces.android.wuha.function.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.muzhi.camerasdk.model.CameraSdkParameterInfo
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.squareup.picasso.Picasso
import com.yingwumeijia.commonlibrary.utils.ListUtil
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * Created by Chris Li on 2017/9/3.
 * 个人账户界面
 */
class AccountActivity : LBaseActivity() {

    val mCameraSdkParameterInfo by lazy { CameraSdkParameterInfo() }

    val request_code_portrait = CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AccountActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        topTitle
        et_user_name.run { isFocusable = true }

        user.setOnClickListener { editPortrait() }
    }


    /**
     * edit portrait
     */
    private fun editPortrait() {
        mCameraSdkParameterInfo.isFilter_image = false
        mCameraSdkParameterInfo.isShow_camera = true
        mCameraSdkParameterInfo.isSingle_mode = true
        mCameraSdkParameterInfo.isCroper_image = true
        val intent = Intent()
        intent.setClassName(application, "com.muzhi.camerasdk.PhotoPickActivity")
        val b = Bundle()
        b.putSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER, mCameraSdkParameterInfo)
        intent.putExtras(b)
        ActivityCompat.startActivityForResult(this, intent, CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) return
        when (requestCode) {
            request_code_portrait -> getBundleForPortrait(data.extras)
        }
    }


    private fun getBundleForPortrait(b: Bundle?) {
        if (b != null) {
            val mCameraSdkParameterInfo = b.getSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER) as CameraSdkParameterInfo
            val list = mCameraSdkParameterInfo.image_list
            if (ListUtil.isEmpty(list)) return Unit
            upLoadPortrait(list[0])
        }
    }

    private fun upLoadPortrait(s: String) {
        Log.d("IMAGE", s)
        Picasso.with(this).load(s).into(user_img)
//        user_img.setImageBitmap(BitmapFactory.decodeFile(s))


    }
}