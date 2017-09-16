package com.pisces.android.wuha.function.mine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.wuha.Constant
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseFragment
import com.pisces.android.wuha.function.mine.logged.LoggedFragment
import com.pisces.android.wuha.function.mine.loginout.LoginOutFragment
import com.pisces.android.wuha.function.user.UserController
import android.content.IntentFilter
import android.util.Log
import android.view.ActionMode
import com.pisces.android.wuha.entity.bean.UserInfoBean
import com.pisces.android.wuha.function.setting.SettingActivity
import kotlinx.android.synthetic.main.mine_frag.*
import rx.functions.Action1


/**
 * Created by Chris Li on 2017/8/31.
 * 我的界面
 */

class MineFragment : LBaseFragment() {


    private val loggedFragment by lazy { LoggedFragment() }

    private val loginOutFragment by lazy { LoginOutFragment() }

    private val loginStatusBroadCastReceive by lazy { LoginStatusBroadCastReceived() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.mine_frag, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (UserController.isLogined(context)) {
            childFragmentManager.beginTransaction().add(R.id.frame_content, loggedFragment).commitAllowingStateLoss()
        } else {
            childFragmentManager.beginTransaction().add(R.id.frame_content, loginOutFragment).commitAllowingStateLoss()
        }

        registerLoginStatusBroadCastReceived()

        setting.setOnClickListener { SettingActivity.start(context) }

    }


    override fun onResume() {
        super.onResume()
        Log.d("JAM", "MINE  onResume")
    }

    fun changeContentFragment(fragment: Fragment) {
        if (childFragmentManager.findFragmentById(R.id.frame_content) == fragment) return Unit
        childFragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commitAllowingStateLoss()
    }


    private fun registerLoginStatusBroadCastReceived() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constant.LOGIN_STATUS_BROADCAST_ACTION)
        context.registerReceiver(loginStatusBroadCastReceive, intentFilter)
    }

    private fun unRegisterLoginStatusBroadCastReceived() {
        context.unregisterReceiver(loginStatusBroadCastReceive)
    }


    override fun onDestroy() {
        unRegisterLoginStatusBroadCastReceived()
        super.onDestroy()
    }

    /**
     * 用户登陆状态改变广播接收
     */
    inner class LoginStatusBroadCastReceived : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val logged = intent!!.getBooleanExtra(Constant.KEY_LOGIN_STATUS, false)
            if (logged) {
                this@MineFragment.changeContentFragment(this@MineFragment.loggedFragment)
            } else {
                this@MineFragment.changeContentFragment(this@MineFragment.loginOutFragment)
            }
        }

    }
}
