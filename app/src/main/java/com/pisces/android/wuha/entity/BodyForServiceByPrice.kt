package com.pisces.android.wuha.entity

/**
 * Created by Chris Li on 2017/9/6.
 * 根据价格返回相应的商铺信息实体类
 */
data class BodyForServiceByPrice(var ServiceProviderType: Int, var CurrentLatitude: Float, var CurrentLongitude: Float, var CurrentPageIndex: Int, var NumberOfResultsPerPage: Int)


//{
//    "ServiceProviderType": 2，     // 服务商类型    1 宠物医疗  2 宠物门店
//    "CurrentPageIndex":1,           //当前页数
//    "NumberOfResultsPerPage":2      //每页显示多少条数据
//}


