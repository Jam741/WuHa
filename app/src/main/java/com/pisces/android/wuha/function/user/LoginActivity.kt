package com.pisces.android.wuha.function.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.mob.MobSDK
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.yingwumeijia.baseywmj.utils.VerifyUtils
import kotlinx.android.synthetic.main.login_act.*


/**
 * Created by Jam on 2017/9/13.
 */
class LoginActivity : LBaseActivity(), LoginContract.View {


    val presenter by lazy { LoginPresenter(this, this) }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
            ActivityCompat.startActivity(context, starter, Bundle.EMPTY)
        }
    }


    override fun setTextWithSendButton(text: String) {
        button.text = text
    }

    override fun resetSendButton() {
        button.text = "重新发送"
    }

    override fun lockSendButton(lock: Boolean) {
        button.isEnabled = !lock
    }

    override fun loginSuccess() {
        close()
    }

    override fun showMsg(msg: String) {
        toastWith(msg)
    }

    var eh: EventHandler = object : EventHandler() {

        override fun afterEvent(event: Int, result: Int, data: Any?) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                when (event) {
                    SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE -> {
                        //提交验证码成功
                        presenter.login(phoneValue())
                    }
                    SMSSDK.EVENT_GET_VERIFICATION_CODE -> {
                        //获取验证码成功
//                        toastWith("短信验证码发送成功")
                    }
                    SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES -> {
                        //返回支持发送验证码的国家列表
                    }
                }
            } else {
                (data as Throwable).printStackTrace()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_act)
        MobSDK.init(this, Config.MOB_APP_KEY, Config.MOB_APP_SECRET)
        SMSSDK.registerEventHandler(eh)

        //发送验证码
        button.setOnClickListener {
            if (verifyPhone(phoneValue()))
                SMSSDK.getVerificationCode("86", phoneValue())
        }

        //校验短信验证码，校验结果在：<EventHandler> 中返回
        btnLogin.setOnClickListener {
            if (verifyPhone(phoneValue()) && verifySmsCode(smsCodeValue())) {
                SMSSDK.submitVerificationCode("86", phoneValue(), smsCodeValue())
            }
        }
    }


    override fun onDestroy() {
        SMSSDK.unregisterEventHandler(eh)
        super.onDestroy()
    }


    fun phoneValue(): String {
        return editText.text.toString()
    }

    fun smsCodeValue(): String {
        return editText2.text.toString()
    }

    fun verifyPhone(phone: String?): Boolean {
        if (!VerifyUtils.verifyMobilePhoneNumber(phone)) {
            toastWith("验证码错误")
            return false
        }
        return true
    }

    fun verifySmsCode(smsCode: String?): Boolean {
        if (!VerifyUtils.verifySmsCode(smsCode)) {
            toastWith("验证码错误")
            return false
        }
        return true
    }
}