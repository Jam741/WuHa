package com.pisces.android.wuha.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.activity_account.*

/**
 * Created by Chris Li on 2017/9/3.
 * 个人账户界面
 */
class AccountActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AccountActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setToolbarTitle("账户")
        et_user_name.run { isFocusable = true }
    }
}