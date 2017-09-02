package com.pisces.android.wuha.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.pisces.android.framworkerlibrary.R
import com.pisces.android.framworkerlibrary.core.JBaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by Chris Li on 2017/9/2.
 * 能隐藏状态栏的基础activity
 */
open class LBaseActivity : JBaseActivity() {


//    private var mToolbar: ImageView? = null


    /**
     * 设置通用的ActionBar Chris Li
     */
    fun setToolbarTitle(title: String) {

//        if (mToolbar == null) {
//            mToolbar = findViewById(R.id.toolbar) as ImageView
//            mToolbar!!.visibility = View.VISIBLE
//            mToolbar!!.setOnClickListener { finish() }
//        }
        toolbar.run {
            visibility = View.VISIBLE
            setOnClickListener { finish() }
        }

        name.run { text = title }

    }


}