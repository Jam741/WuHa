package com.yingwumeijia.baseywmj.utils.net.interceptor


import com.pisces.android.wuha.function.user.AccountManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Jam on 2017/2/15 下午5:04.
 * Describe: 用户身份验证信息  拦截器
 */

class AccountInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("access-token", "BasicAuth " + AccountManager.IdentityToken)
                .build()
        return chain.proceed(request)
    }
}
