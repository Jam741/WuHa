package com.pisces.android.wuha.function.user

import android.content.Context
import com.pisces.android.framworkerlibrary.utlis.SPUtils
import com.pisces.android.wuha.Constant
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
     * 设置用户登录状态
     *
     * @param loginStatus 登录状态
     */
    fun setLoginStatus(context: Context, loginStatus: Boolean) {
        SPUtils.put(context, Constant.KEY_LOGIN_STATUS, loginStatus)
    }

    /**
     * 返回用户登录状态
     *
     * @return 是否登录
     */
    fun isLogined(context: Context): Boolean {
        return SPUtils.get(context, Constant.KEY_LOGIN_STATUS, false) as Boolean
    }


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