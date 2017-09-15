package com.pisces.android.wuha.function.user

import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import cn.smssdk.SMSSDK
import com.orhanobut.logger.Logger
import com.pisces.android.framworkerlibrary.utlis.Installation
import com.pisces.android.wuha.entity.bean.LoginResponse
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.pisces.android.wuha.tools.KeyPairGenUtil
import com.pisces.android.wuha.tools.RSAUtils
import rx.functions.Action1

/**
 * Created by Jam on 2017/9/14.
 */
class LoginPresenter(val context: Context, val view: LoginContract.View) : LoginContract.Presenter {


    val total_time: Long = 60000
    val cycles_time: Long = 1000

    val countDownTimer: CountDownTimer by lazy {
        object : CountDownTimer(total_time, cycles_time) {

            override fun onTick(millisUntilFinished: Long) {
                view.setTextWithSendButton((millisUntilFinished / cycles_time).toString() + "s")
            }

            override fun onFinish() {
                view.resetSendButton()
                view.lockSendButton(false)
            }
        }
    }

    override fun login(phone: String) {
        val bodyForLogin = BodyForLogin()
        bodyForLogin.deviceName = Build.DEVICE

//        val currentDeviceIdentificationNumber = RSAUtils.encryptByPublicKey(Installation.id(context))
//        Logger.d(currentDeviceIdentificationNumber)
//        Log.d("JAM", currentDeviceIdentificationNumber)
//        bodyForLogin.currentDeviceIdentificationNumber = currentDeviceIdentificationNumber
//        val phone = RSAUtils.encryptByPublicKey(phone)
//        Logger.d(phone)
//        Log.d("JAM", phone)
//        bodyForLogin.mobliePhoneNumber = phone
//        UserControler.login(context, bodyForLogin, Action1<LoginResponse> {
//            view.loginSuccess()
//        })
//
//        HttpUtli.toSubscribe(Api.service.login(bodyForLogin), object : ProgressSubscriber<LoginResponse>(context) {
//            override fun onSuccess(t: LoginResponse?) {
//                if (t == null) return
//                AccountManager.refreshIdentityToken(context, t.identityToken)
//                view.loginSuccess()
//            }
//        })


        val keyP = KeyPairGenUtil()

        keyP.run()
    }

    override fun sendSmsCode(phone: String) {
        SMSSDK.getVerificationCode("86", phone)
        view.lockSendButton(true)
        view.setTextWithSendButton("60s")
        countDownTimer.start()
        view.showMsg("短信验证码发送成功，请注意查收")
    }


}