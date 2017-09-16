package com.pisces.android.wuha.function.mine

import android.view.LayoutInflater
import android.view.View
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseFragment
import com.pisces.android.wuha.function.collect.CollectActivity
import com.pisces.android.wuha.function.personage.CallActivity
import com.pisces.android.wuha.function.personage.ShareActivity
import com.pisces.android.wuha.function.personage.SuggestActivity
import com.pisces.android.wuha.function.user.UserController

/**
 * Created by Jam on 2017/9/16.
 */
open class BaseMineContentFragment : LBaseFragment() {

    val menusView by lazy { createMenusView() }

    private fun createMenusView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.mine_menu_list, null)
        view.findViewById(R.id.collect).setOnClickListener { if (UserController.passPrecondition(context)) CollectActivity.start(context) }
        view.findViewById(R.id.message).setOnClickListener { SuggestActivity.start(context) }
        view.findViewById(R.id.call).setOnClickListener { CallActivity.start(context) }
        view.findViewById(R.id.share).setOnClickListener { ShareActivity.start(context) }
        return view
    }
}