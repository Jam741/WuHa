package com.pisces.android.wuha.net.api

import com.pisces.android.wuha.entity.BodyForServiceByDistance
import com.pisces.android.wuha.entity.BodyForServiceByPrice
import com.pisces.android.wuha.entity.bean.ServiceProviderResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Jam on 2017/9/2.
 *
 * 网络请求服务
 */
interface ApiService {


    /**
     *
     * 根据距离查询服务商列表
     *
     * @param bodyForServiceByDistance
     *
     * @return
     */
    @POST("ServiceProvider/GetServiceProviderByDistance")
    fun getServiceFromLocation(@Body bodyForServiceByDistance: BodyForServiceByDistance): Observable<ServiceProviderResponse>


    /**
     *
     * 根据价格查询服务商列表
     *
     * @param bodyForServiceByPrice
     *
     * @return
     */
    @POST("ServiceProvider/GetServiceProviderByStartingPrice")
    fun getServiceFromPrice(@Body bodyForServiceByPrice: BodyForServiceByPrice): Observable<ServiceProviderResponse>

}