package com.pisces.android.wuha.function.personage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.setting.bean.FeedBack
import com.pisces.android.wuha.net.HttpUtli
import com.pisces.android.wuha.net.api.Api
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * Created by Chris Li on 2017/9/1.
 * 意见建议界面
 */


class SuggestActivity : LBaseActivity() {
    lateinit var mMessage:String
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SuggestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initView()
    }

    private fun initView() {
        topLeft.setOnClickListener {
            close()
        }
        topTitle.text = "意见建议"

        put_in.setOnClickListener {
            if (verifyMessage(messageValue())) {
                HttpUtli.toSubscribe(Api.service.addFeedBack(FeedBack("意见和建议", mMessage, 0, "")), object : SimpleSubscriber<Any>(this@SuggestActivity) {
                    override fun onSuccess(t: Any?) {
                        Toast.makeText(this@SuggestActivity, "提交成功", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
            }
        }

    }

    /**
     * 检查输入框的内容是否为空
     */
    private fun verifyMessage(messageValue: String): Boolean {
        if (!TextUtils.isEmpty(messageValue)) {
            mMessage = messageValue
            return true
        }
        Toast.makeText(this, "请输入你的意见或建议", Toast.LENGTH_SHORT).show()
        return false
    }

    /**
     * 获取输入框的内容
     */
    private fun messageValue(): String {
        return message.text.toString()
    }
}
