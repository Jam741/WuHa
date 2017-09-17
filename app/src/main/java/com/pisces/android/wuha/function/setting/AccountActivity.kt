package com.pisces.android.wuha.function.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.muzhi.camerasdk.model.CameraSdkParameterInfo
import com.pisces.android.wuha.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.squareup.picasso.Picasso
import com.yingwumeijia.baseywmj.utils.VerifyUtils
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

    val userName by lazy { intent.getStringExtra(Constant.KEY_CURRENT) }


    companion object {
        fun start(context: Context, username: String) {
            val intent = Intent(context, AccountActivity::class.java)
            intent.putExtra(Constant.KEY_CURRENT, username)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        topTitle.text = "账户"
        et_user_name.setText(userName)


        topLeft.setOnClickListener { close() }
        user.setOnClickListener { editPortrait() }
        et_user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                user_name_delete.visibility = if (usernameValue().isNotEmpty()) View.VISIBLE else View.GONE
            }
        })

        user_name_delete.setOnClickListener { et_user_name.setText("") }
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


    private fun usernameValue(): String {
        return et_user_name.text.toString()
    }

    private fun verifyUserName(username: String?): Boolean {
        if (!VerifyUtils.verifyUsername(username)) {
            toastWith("请输入正确的用户名")
            return false
        }
        return true
    }
}