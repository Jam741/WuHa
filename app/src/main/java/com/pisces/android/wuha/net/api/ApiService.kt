package com.pisces.android.wuha.net.api

import com.pisces.android.wuha.entity.BodyForServiceByCount
import com.pisces.android.wuha.entity.BodyForServiceByDistance
import com.pisces.android.wuha.entity.BodyForServiceByPrice
import com.pisces.android.wuha.entity.BodyForServiceDetailById
import com.pisces.android.wuha.entity.bean.LoginResponse
import com.pisces.android.wuha.entity.bean.ServiceDetailProvider
import com.pisces.android.wuha.entity.bean.ServiceProvider
import com.pisces.android.wuha.function.collect.BodyCollect
import com.pisces.android.wuha.function.setting.bean.FeedBack
import com.pisces.android.wuha.function.user.BodyForLogin
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
    fun getServiceFromLocation(@Body bodyForServiceByDistance: BodyForServiceByDistance): Observable<ArrayList<ServiceProvider>>


    /**
     *
     * 根据价格查询服务商列表
     *
     * @param bodyForServiceByPrice
     *
     * @return
     */
    @POST("ServiceProvider/GetServiceProviderByStartingPrice")
    fun getServiceFromPrice(@Body bodyForServiceByPrice: BodyForServiceByPrice): Observable<ArrayList<ServiceProvider>>


    /**
     * 根据人气查询服务商列表
     *
     * @param bodyForServiceByCount
     */
    @POST("ServiceProvider/GetServiceProviderByViewingCount")
    fun getServiceFromCount(@Body bodyForServiceByCount: BodyForServiceByCount): Observable<ArrayList<ServiceProvider>>


    /**
     * 根据UserId查询个人收藏
     *
     * @param bodyCollect
     *
     */
    @POST("UserInfo/GetUserFavorites")
    fun getUserFavorites(@Body bodyCollect: BodyCollect): Observable<ArrayList<ServiceProvider>>


    /**
     * 登陆
     *
     * @param bodyForLogin
     */
    @POST("/api/Account/AccountLogin")
    fun login(@Body bodyForLogin: BodyForLogin): Observable<LoginResponse>


    /**
     * 根据id获取商铺详情
     */
    @POST("ServiceProvider/GetServiceProviderDetail")
    fun getServiceProviderDetail(@Body bodyForServiceByDistance: BodyForServiceDetailById): Observable<ServiceDetailProvider>


    /**
     * 提交反馈或意见
     */
    @POST("About/AddFeedBack")
    fun addFeedBack(@Body feedBack: FeedBack): Observable<Any>

}