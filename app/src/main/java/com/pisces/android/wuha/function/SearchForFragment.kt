package com.pisces.android.wuha.function

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.wuha.R

/**
 * Created by Chris Li on 2017/9/3.
 */
class SearchForFragment : JBaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.activity_search_for, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}