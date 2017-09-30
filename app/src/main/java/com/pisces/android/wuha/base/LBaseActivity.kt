package com.pisces.android.wuha.base

import android.widget.Toast
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.widget.WuHaProgressDialog
import com.umeng.analytics.MobclickAgent


/**
 * Created by Chris Li on 2017/9/2.
 */
open class LBaseActivity : JBaseActivity() {

    private val progressDialog by lazy { WuHaProgressDialog(this) }

    private var toast: Toast? = null


    fun toastWith(message: String) {
        if (toast == null) toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        else toast!!.setText(message)
        toast!!.show()
    }

    fun toastWith(messageRes: Int) {
        if (toast == null) toast = Toast.makeText(this, messageRes, Toast.LENGTH_SHORT)
        else toast!!.setText(messageRes)
        toast!!.show()
    }

    fun showDialog(){
        progressDialog.show()
    }

    fun dismissDialog(){
        progressDialog.dismiss()
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}