package com.pisces.android.wuha.net

import android.content.Context
import android.widget.Toast
import com.pisces.android.framworkerlibrary.net.exception.ApiException
import com.pisces.android.wuha.net.subscriber.ProgressSubscriber
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers
import com.pisces.android.wuha.net.subscriber.SimpleSubscriber
import rx.functions.Action1
import java.net.ConnectException
import java.net.SocketTimeoutException


/**
 * Created by Jam on 2017/9/2.
 */
object HttpUtli {


    /**
     * 网络请求统一的线程调度
     *
     * @param <T>
     * @return
    </T> */
    fun <T> applySchedulers(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
        }
    }


    /**
     * 网络请求异常同意处理
     */
    fun disposeHttpException(context: Context, e: Throwable?) {

        val message: String?
        if (e is ApiException) {
            message = e.error_message
        } else if (e is ConnectException) {
            message = "网络连接异常，请重试"
        } else if (e is SocketTimeoutException) {
            message = "网络请求超时，请重试"
        } else {
            message = "网络异常"
        }
        e!!.printStackTrace()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun <T> toSubscribe(ob: Observable<T>, subscriber: ProgressSubscriber<T>) {
        ob.compose(applySchedulers())
                .doOnSubscribe {
                    subscriber.progress.show()
                }
                .subscribe(subscriber)
    }

    fun <T> toSubscribe(ob: Observable<T>, subscriber: SimpleSubscriber<T>) {
        ob.compose(applySchedulers())
                .subscribe(subscriber)
    }

    fun <T> toSubscribe(ob: Observable<T>, subscriber: Subscriber<T>) {
        ob.compose(applySchedulers())
                .subscribe(subscriber)
    }

    fun <T> toSubscribe(ob: Observable<T>, action: Action1<T>) {
        ob.compose(applySchedulers())
                .subscribe(action)
    }

}