package com.pisces.android.wuha.base

import android.widget.Toast
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.widget.WuHaProgressDialog


/**
 * Created by Chris Li on 2017/9/2.
 * 能隐藏状态栏的基础activity
 */
open class LBaseActivity : JBaseActivity() {

    val progressDialog by lazy { WuHaProgressDialog(this) }

    var toast: Toast? = null


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

}