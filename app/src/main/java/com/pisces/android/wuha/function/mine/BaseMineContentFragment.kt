package com.pisces.android.wuha.function.mine

import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.pisces.android.sharesdk.ShareBean
import com.pisces.android.sharesdk.ShareClient
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseFragment
import com.pisces.android.wuha.function.collect.CollectActivity
import com.pisces.android.wuha.function.personage.SuggestActivity
import com.pisces.android.wuha.function.user.UserController

/**
 * Created by Jam on 2017/9/16.
 */
open class BaseMineContentFragment : LBaseFragment() {

    val menusView by lazy { createMenusView() }

    val shareClient by lazy { ShareClient(activity, ShareBean("呜哈", "呜哈，专业的宠物信息服务平台", "https://www.pisces91.com/", "http://owq0wloan.bkt.clouddn.com/logo.png")) }


    private fun createMenusView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.mine_menu_list, null)
        view.findViewById(R.id.collect).setOnClickListener { if (UserController.passPrecondition(context)) CollectActivity.start(context) }
        view.findViewById(R.id.message).setOnClickListener { SuggestActivity.start(context) }
        view.findViewById(R.id.call).setOnClickListener {
            AlertDialog
                    .Builder(context)
                    .setTitle("联系我们")
                    .setMessage("邮箱：customerservice@pisces91.com")
                    .setPositiveButton("知道了", { dialog, which -> dialog.dismiss() })
                    .show()
        }
        view.findViewById(R.id.share).setOnClickListener { v ->
            shareClient.launchSharePopWindow(v)
        }
        return view
    }
}