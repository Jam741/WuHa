package com.pisces.android.wuha.function.collect

import com.pisces.android.locationlibrary.Constant

/**
 * Created by Chris Li on 2017/9/12.
 */
data class BodyCollect(var UserId: String) {
    
    var CurrentLatitude: Float = Constant.getGpsY()
    var CurrentLongitude: Float = Constant.getGpsX()
}