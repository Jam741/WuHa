package com.pisces.android.wuha.function.user

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.pisces.android.framworkerlibrary.utlis.SPUtils
import com.pisces.android.wuha.Constant
import com.pisces.android.wuha.entity.bean.LoginResponse
import com.pisces.android.wuha.entity.bean.UserInfoBean
import com.pisces.android.wuha.function.mine.BodyForUserInfo
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.umeng.analytics.MobclickAgent
import okhttp3.ResponseBody
import rx.functions.Action1

/**
 * Created by Jam on 2017/9/14.
 *
 * 用户级别权限操作  控制器 ：数据对外，逻辑不对外
 */
object UserController {


    /**
     * 用户操作先决条件
     */
    fun passPrecondition(context: Context): Boolean {
        if (!isLogined(context)) {
            LoginActivity.start(context)
            return false
        }
        return true
    }

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
     * 获取UserId
     */
    fun userId(context: Context): Int {
        val userId = SPUtils.get(context, Constant.KEY_USER_ID_CACHE, 0) as Int
        Log.d("JAM", "=====UserId:" + userId)
        return userId
    }

    /**
     * 刷新UserId
     */
    fun refreshUserId(context: Context, userId: Int) {
        SPUtils.put(context, Constant.KEY_USER_ID_CACHE, userId)
    }


    fun getUserInfoBean(context: Context): UserInfoBean? {
        val jsonData = SPUtils.get(context, Constant.KEY_USER_INFO_CACHE, "") as String
        if (TextUtils.isEmpty(jsonData)) return null
        return Gson().fromJson(jsonData, UserInfoBean::class.java)
    }

    fun refreshUserInfo(context: Context, userInfoBean: UserInfoBean) {
        SPUtils.put(context, Constant.KEY_USER_INFO_CACHE, Gson().toJson(userInfoBean))
    }

    /**
     * 用户登录操作
     */
    fun login(context: Context, bodyForLogin: BodyForLogin, subscriber: Action1<LoginResponse>) {
        HttpUtli.toSubscribe(Api.service.login(bodyForLogin), object : ProgressSubscriber<LoginResponse>(context) {
            override fun onSuccess(t: LoginResponse?) {
                if (t == null) return
                refreshUserId(context, t.id)
                setLoginStatus(context, true)
                AccountManager.refreshIdentityToken(context, t.identityToken)
                subscriber.call(t)
                sendLoginStatusChangedBroadCast(context, true)
                MobclickAgent.onProfileSignIn(t.id.toString())
            }
        })
    }


    /**
     * 获取用户信息
     */
    fun getUserInfo(context: Context, action1: Action1<UserInfoBean>) {
        HttpUtli.toSubscribe(Api.service.getUserInfo(BodyForUserInfo().apply { accountId = userId(context) }), object : ProgressSubscriber<UserInfoBean>(context) {
            override fun onSuccess(t: UserInfoBean?) {
                refreshUserInfo(context, t!!)
                action1.call(t!!)
            }
        })
    }


    /**
     * 用户退出登录操作
     */
    fun loginOut(context: Context) {
        setLoginStatus(context, false)
        //清除缓存的用户信息
        SPUtils.remove(context, Constant.KEY_USER_ID_CACHE)
        //发送用户登录状态改变的广播
        sendLoginStatusChangedBroadCast(context, false)
        MobclickAgent.onProfileSignOff()
    }


    /**
     * 发送用户登录状态改变的广播
     *
     * @param loginStatus 登录状态
     */
    fun sendLoginStatusChangedBroadCast(context: Context, loginStatus: Boolean) {
        val intent = Intent()
        intent.putExtra(Constant.KEY_LOGIN_STATUS, loginStatus)
        intent.action = Constant.LOGIN_STATUS_BROADCAST_ACTION
        context.sendBroadcast(intent)
    }
}