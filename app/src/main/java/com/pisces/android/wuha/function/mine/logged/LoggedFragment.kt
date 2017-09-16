package com.pisces.android.wuha.function.mine.logged

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.wuha.R
import com.pisces.android.wuha.function.mine.BaseMineContentFragment
import kotlinx.android.synthetic.main.mine_logged_layout.*

/**
 * Created by Jam on 2017/9/16.
 */
class LoggedFragment : BaseMineContentFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.mine_logged_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frame_menus.addView(menusView)
    }
}