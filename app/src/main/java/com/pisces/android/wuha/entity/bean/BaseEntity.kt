package com.pisces.android.wuha.entity.bean

import com.pisces.android.framworkerlibrary.net.ApiModel

/**
 * Created by jamisonline on 2017/9/11.
 *
 * 通过接口请求返回数据的基类
 */
 open class BaseEntity<T> : ApiModel<T>()