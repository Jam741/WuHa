package com.pisces.android.wuha.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pisces.android.framworkerlibrary.core.JBaseFragment

import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/1.
 * 商家详情界面里面的商家信息界面
 */

class ClientMessageFragment : JBaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.f_client_message, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
