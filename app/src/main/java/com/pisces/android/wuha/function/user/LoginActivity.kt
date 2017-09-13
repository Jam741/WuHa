package com.pisces.android.wuha.function.user

import android.os.Bundle
import android.os.PersistableBundle
import com.pisces.android.wuha.base.LBaseActivity
import cn.smssdk.SMSSDK.getNewFriendsCount
import cn.smssdk.SMSSDK.registerEventHandler
import com.mob.tools.utils.UIHandler.sendMessage
import android.widget.Toast
import com.mob.MobSDK
import cn.smssdk.SMSSDK.setAskPermisionOnReadContact

import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import android.os.Handler
import android.os.Handler.Callback
import android.os.Message


/**
 * Created by Jam on 2017/9/13.
 */
class LoginActivity:LBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    private fun registerSDK() {
        // 在尝试读取通信录时以弹窗提示用户（可选功能）
        SMSSDK.setAskPermisionOnReadContact(true)

        val handler = Handler(object :Callback{
            override fun handleMessage(msg: Message?): Boolean {
//                if (pd != null && pd.isShowing()) {
//                    pd.dismiss()
//                }
//
//                val event = msg.arg1
//                val result = msg.arg2
//                val data = msg.obj
//                if (event == SMSSDK.EVENT_SUBMIT_USER_INFO) {
//                    // 短信注册成功后，返回MainActivity,然后提示新好友
//                    if (result == SMSSDK.RESULT_COMPLETE) {
//                        Toast.makeText(this, R.string.smssdk_user_info_submited, Toast.LENGTH_SHORT).show()
//                    } else {
//                        (data as Throwable).printStackTrace()
//                    }
//                } else if (event == SMSSDK.EVENT_GET_NEW_FRIENDS_COUNT) {
//                    if (result == SMSSDK.RESULT_COMPLETE) {
//                        refreshViewCount(data)
//                        gettingFriends = false
//                    } else {
//                        (data as Throwable).printStackTrace()
//                    }
//                }
                return false            }
        })
        val eventHandler = object : EventHandler() {
//            fun afterEvent(event: Int, result: Int, data: Any) {
//                val msg = Message()
//                msg.arg1 = event
//                msg.arg2 = result
//                msg.obj = data
//                handler.sendMessage(msg)
//            }
        }
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler)
//        ready = true

        // 获取新好友个数
        showDialog()
        SMSSDK.getNewFriendsCount()
//        gettingFriends = true
    }

}