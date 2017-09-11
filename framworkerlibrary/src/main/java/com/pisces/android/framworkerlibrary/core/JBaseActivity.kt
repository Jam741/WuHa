package com.pisces.android.framworkerlibrary.core

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
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


}