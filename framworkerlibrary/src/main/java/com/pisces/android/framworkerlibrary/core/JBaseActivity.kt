package com.pisces.android.framworkerlibrary.core

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.pisces.android.framworkerlibrary.R
import rx.subjects.PublishSubject

/**
 * Created by Jam on 2017/8/21.
 */
open class JBaseActivity : AppCompatActivity() {

    val lifecycleSubject = PublishSubject.create<ActivityLifeCycleEvent>()


    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESUME)
        super.onResume()
    }

    override fun onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY)
        super.onDestroy()
    }

    override fun onStart() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.START)
        super.onStart()
    }

    override fun onRestart() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESTART)
        super.onRestart()
    }

    /**
     * 关闭当前页
     */
    fun close() {
        ActivityCompat.finishAfterTransition(this)
    }


    private var mToolbar: ImageView? = null
    private var mToolbarName: TextView? = null

    /**
     * 设置通用的ActionBar Chris Li
     */
    fun setToolbarTitle(title: String) {

        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar) as ImageView
            mToolbar!!.visibility = View.VISIBLE
            mToolbar!!.setOnClickListener { finish() }
        }
        if (mToolbarName == null) {
            mToolbarName = findViewById(R.id.toolbar_center_name) as TextView
        }
        mToolbarName!!.text = title
    }


    /**
     * 设置隐藏标题栏 Chris Li
     */
    fun setNoTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    /**
     * 设置隐藏状态栏 Chris Li
     */
    fun setFullscreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}