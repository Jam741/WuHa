package com.pisces.android.wuha.base

import android.widget.Toast
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.wuha.widget.WuHaProgressDialog

/**
 * Created by Jam on 2017/9/11.
 */
open class LBaseFragment :JBaseFragment() {



    private val progressDialog by lazy { WuHaProgressDialog(context) }

    private var toast: Toast? = null


    fun toastWith(message: String) {
        if (toast == null) toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        else toast!!.setText(message)
        toast!!.show()
    }

    fun toastWith(messageRes:Int){
        if (toast == null) toast = Toast.makeText(context, messageRes, Toast.LENGTH_SHORT)
        else toast!!.setText(messageRes)
        toast!!.show()
    }

    fun showDialog(){
        if (activity is LBaseActivity)
            (activity as LBaseActivity).showDialog()
        else
            progressDialog.show()
    }

    fun dismissDialog(){
        if (activity is LBaseActivity)
            (activity as LBaseActivity).dismissDialog()
        else
            progressDialog.dismiss()
    }


}