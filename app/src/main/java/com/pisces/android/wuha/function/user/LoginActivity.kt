package com.pisces.android.wuha.function.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.mob.MobSDK
import com.orhanobut.logger.Logger
import com.pisces.android.wuha.Config
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.tools.JRSAUtils
import com.pisces.android.wuha.tools.RSAUtils
import com.yingwumeijia.baseywmj.utils.VerifyUtils
import kotlinx.android.synthetic.main.login_act.*


/**
 * Created by Jam on 2017/9/13.
 */
class LoginActivity : LBaseActivity(), LoginContract.View {


    val presenter by lazy { LoginPresenter(this, this) }

    val handler by lazy {
        createHandler()
    }

    private fun createHandler(): Handler {
        return object : Handler(this.mainLooper) {

            override fun handleMessage(msg: Message?) {
                //回调完成
                when (msg!!.what) {
                    SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE -> {
                        //提交验证码成功
                        presenter.login(phoneValue())
                    }
                    SMSSDK.EVENT_GET_VERIFICATION_CODE -> {
                        //获取验证码成功
                    }
                    SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES -> {
                        //返回支持发送验证码的国家列表
                    }
                }
            }
        }
    }


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
            runOnUiThread {
                Logger.d(data)
                if (result == SMSSDK.RESULT_COMPLETE) {
                    handler.handleMessage(Message().apply { what = event })
                } else {
                    (data as Throwable).printStackTrace()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_act)
        SMSSDK.registerEventHandler(eh)

        //发送验证码
        button.setOnClickListener {
            if (verifyPhone(phoneValue()))
                presenter.sendSmsCode(phoneValue())
        }

        //校验短信验证码，校验结果在：<EventHandler> 中返回
        btnLogin.setOnClickListener {


            Log.d("RSA", JRSAUtils.encryptByPublicKey("13041652801"))

            val str = "123456787654"

            Log.d("RSA", "加密前：" + str)
            val mss = RSAUtils.encrypt(str)
            Log.d("RSA", "加密后：" + mss)
            val jmss = RSAUtils.decrypt(mss)
            Log.d("RSA", "解密后：" + jmss)


//            if (verifyPhone(phoneValue()) && verifySmsCode(smsCodeValue())) {
//                SMSSDK.submitVerificationCode("86", phoneValue(), smsCodeValue())
//            }
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
            toastWith("手机号码错误")
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