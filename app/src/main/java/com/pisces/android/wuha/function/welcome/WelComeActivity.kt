package com.pisces.android.wuha.function.welcome

import android.os.Bundle
import android.os.Handler
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.main.MainActivity

/**
 * Created by jamisonline on 2017/9/25.
 */
class WelComeActivity : JBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_act)

        Handler().postDelayed({
            MainActivity.statr(this)
        }, 3000)
    }
}