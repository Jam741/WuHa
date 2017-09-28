package com.pisces.android.wuha.function.user

import android.content.Context
import android.os.CountDownTimer
import com.pisces.android.framworkerlibrary.utlis.Installation
import com.pisces.android.wuha.function.shop.bean.BodySendSmsCode
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import com.yingwumeijia.baseywmj.utils.VerifyUtils
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

    override fun login(phone: String, smsCode: String) {
        if (!verifyPhone(phone) || !verifySmsCode(smsCode)) return
        val bodyForLogin = BodyForLogin()
        bodyForLogin.deviceName = android.os.Build.MODEL
        bodyForLogin.currentDeviceIdentificationNumber = Installation.id(context)
        bodyForLogin.mobliePhoneNumber = phone
        UserController.login(context, bodyForLogin, Action1 {
            view.loginSuccess()
        })
    }

    override fun sendSmsCode(phone: String) {
        if (!verifyPhone(phone)) return
        HttpUtli.toSubscribe(Api.service.sendSmsCode(BodySendSmsCode(phone)), object : ProgressSubscriber<Int>(context) {
            override fun onSuccess(t: Int?) {
                view.lockSendButton(true)
                view.setTextWithSendButton("60s")
                countDownTimer.start()
                view.showMsg("短信验证码发送成功，请注意查收")
            }

        })
    }

    private fun verifyPhone(phone: String?): Boolean {
        if (!VerifyUtils.verifyMobilePhoneNumber(phone)) {
            view.showMsg("手机号码错误")
            return false
        }
        return true
    }

    private fun verifySmsCode(smsCode: String?): Boolean {
        if (!VerifyUtils.verifySmsCode(smsCode)) {
            view.showMsg("验证码错误")
            return false
        }
        return true
    }
}