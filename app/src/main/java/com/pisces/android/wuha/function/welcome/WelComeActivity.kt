package com.pisces.android.wuha.function.welcome

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.guide.GuideActivity
import com.pisces.android.wuha.function.main.MainActivity

/**
 * Created by jamisonline on 2017/9/25.
 */
class WelComeActivity : JBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*set it to be full screen*/
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.welcome_act)

        Handler().postDelayed({
            close()

            if (!GuideActivity.isViewGuide(this@WelComeActivity)){
                GuideActivity.start(this)
            }else{
                MainActivity.statr(this)
            }
        }, 3000)
    }
}