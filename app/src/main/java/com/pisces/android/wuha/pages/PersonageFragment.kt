package com.pisces.android.wuha.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.wuha.R
import com.pisces.android.wuha.collect.CollectActivity
import com.pisces.android.wuha.mine.CallActivity
import com.pisces.android.wuha.mine.MessageActivity
import com.pisces.android.wuha.mine.ShareActivity
import com.pisces.android.wuha.setting.AccountActivity
import com.pisces.android.wuha.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_personage.*

/**
 * Created by Chris Li on 2017/8/31.
 * 我的界面
 */

class PersonageFragment : JBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_personage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setting.setOnClickListener { SettingActivity.start(activity) }
        collect.setOnClickListener { CollectActivity.start(activity) }
        message.setOnClickListener { MessageActivity.start(activity) }
        call.setOnClickListener { CallActivity.start(activity) }
        share.setOnClickListener { ShareActivity.start(activity) }
        top_logo.setOnClickListener { AccountActivity.start(activity) }
    }

}
