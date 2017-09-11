package com.pisces.android.wuha.net

import android.util.Log
//import com.pisces.android.framworkerlibrary.net.converter.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Jam on 2017/9/2.
 */
class RetrofitUtli private constructor() {

    fun baseUrl(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? -> Log.d("HTTP", message) }).setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build()
    }

    private object RetrofitUtli {
        val INSTANCE = RetrofitUtli()
    }

    companion object {
        val instance by lazy { RetrofitUtli.INSTANCE }
    }


}