package com.pisces.android.wuha.function.user

import android.content.Context
import android.util.Log
import com.pisces.android.framworkerlibrary.JBaseApplication
import com.pisces.android.framworkerlibrary.utlis.SPUtils
import com.pisces.android.wuha.App
import com.pisces.android.wuha.Constant

/**
 * Created by Jam on 2017/9/14.
 */
object AccountManager {

    fun identityToken(): String {
        val token = SPUtils.get(JBaseApplication.appContext(), Constant.KEY_IDENTITY_TOKEN, "") as String
        Log.d("TOKEN", token)
        return token
    }

    fun refreshIdentityToken(context: Context, identityToken: String) {
        SPUtils.put(context, Constant.KEY_IDENTITY_TOKEN, identityToken)
    }
}