package com.pisces.android.wuha.function.user

import com.pisces.android.framworkerlibrary.core.JBasePresenter
import com.pisces.android.framworkerlibrary.core.JBaseView

/**
 * Created by Jam on 2017/9/14.
 */
interface LoginContract {

    interface View : JBaseView {

        fun setTextWithSendButton(text: String)

        fun resetSendButton()

        fun lockSendButton(lock: Boolean)

        fun loginSuccess()

        fun showMsg(msg: String)
    }

    interface Presenter : JBasePresenter {


        fun login(phone: String)

        fun sendSmsCode(phone: String)


    }


}