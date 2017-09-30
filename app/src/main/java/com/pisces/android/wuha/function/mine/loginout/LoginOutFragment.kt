package com.pisces.android.wuha.function.mine.loginout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.mine.BaseMineContentFragment
import com.pisces.android.wuha.function.setting.AccountActivity
import com.pisces.android.wuha.function.user.LoginActivity
import kotlinx.android.synthetic.main.mine_loginout_layout.*

/**
 * Created by Jam on 2017/9/16.
 */
class LoginOutFragment : BaseMineContentFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.mine_loginout_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (menusView.parent != null) {
            val parent = menusView.parent as ViewGroup
            parent.removeAllViews()
        }
        frame_menus.addView(menusView)
        ivPortrait.setImageResource(R.mipmap.mine_top_photo)
        ivPortrait.setOnClickListener { LoginActivity.start(context) }
        btnLogin.setOnClickListener { LoginActivity.start(context) }
    }
}