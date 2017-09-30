package com.pisces.android.wuha.function.collect

import com.pisces.android.locationlibrary.Constant

/**
 * Created by Chris Li on 2017/9/12.
 */
data class BodyCollect(var UserId: String) {
    //"UserId": "string"//用户id
    //        "CurrentLatitude": 0,// 经度
//        "CurrentLongitude": 0//维度
    var CurrentLatitude: Float = Constant.getGpsX()
    val CurrentLongitude: Float = Constant.getGpsY()
}