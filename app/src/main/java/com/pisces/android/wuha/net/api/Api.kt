package com.pisces.android.wuha.net.api

import com.pisces.android.wuha.Config
import com.pisces.android.wuha.net.RetrofitUtli

/**r5
 * Created by Jam on 2017/9/2.
 */
class Api {

    private object ApiServiceINSTANCE {
        val INSTANCE =RetrofitUtli.instance.baseUrl(Config.host).create(ApiService::class.java)
    }

    companion object {
        val service by lazy { ApiServiceINSTANCE.INSTANCE }
    }

}