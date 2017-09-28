package com.pisces.android.wuha.function.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.login_act.*


/**
 * Created by Jam on 2017/9/13.
 * 登陆界面
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
        btnSendSmsCode.text = text
    }

    override fun resetSendButton() {
        btnSendSmsCode.text = "重新发送"
    }

    override fun lockSendButton(lock: Boolean) {
        btnSendSmsCode.isEnabled = !lock
    }

    override fun loginSuccess() {
        close()
    }

    override fun showMsg(msg: String) {
        toastWith(msg)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_act)

        //发送验证码
        btnSendSmsCode.setOnClickListener {
            presenter.sendSmsCode(phoneValue())
        }

        btnLogin.setOnClickListener {
            presenter.login(phoneValue(), smsCodeValue())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }


    fun phoneValue(): String = editText.text.toString()

    private fun smsCodeValue(): String = editText2.text.toString()


}