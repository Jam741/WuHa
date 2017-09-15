package com.pisces.android.wuha.function.user

import android.content.Context
import com.pisces.android.wuha.entity.bean.LoginResponse
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import rx.functions.Action1

/**
 * Created by Jam on 2017/9/14.
 *
 * 用户级别权限操作  控制器 ：数据对外，逻辑不对外
 */
object UserControler {


    /**
     * 用户登录操作
     */
    fun login(context: Context, bodyForLogin: BodyForLogin, subscriber: Action1<LoginResponse>) {
        HttpUtli.toSubscribe(Api.service.login(bodyForLogin), object : ProgressSubscriber<LoginResponse>(context) {
            override fun onSuccess(t: LoginResponse?) {
                if (t == null) return
                AccountManager.refreshIdentityToken(context, t.identityToken)
                subscriber.call(t)
            }
        })
    }


    /**
     * 用户退出登录操作
     */
    fun loginOut() {

    }
}