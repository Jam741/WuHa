package com.pisces.android.wuha.net.api

import com.pisces.android.wuha.entity.*
import com.pisces.android.wuha.entity.bean.*
import com.pisces.android.wuha.function.collect.BodyCollect


import com.pisces.android.wuha.function.mine.BodyForUserInfo
import com.pisces.android.wuha.function.search.BodySearch
import com.pisces.android.wuha.function.setting.bean.BodyPhoto
import com.pisces.android.wuha.function.setting.bean.BodyUserName
import com.pisces.android.wuha.function.setting.bean.FeedBack
import com.pisces.android.wuha.function.shop.BodyForCollect
import com.pisces.android.wuha.function.shop.bean.BodyAddViewingCount
import com.pisces.android.wuha.function.shop.bean.BodySendSmsCode

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
    @POST("Account/AccountLogin")
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

    /**
     * 获取用户信息
     */
    @POST("UserInfo/GetUserInfo")
    fun getUserInfo(@Body bodyForUserInfo: BodyForUserInfo): Observable<UserInfoBean>


    /**
     * 返回所有用户的热搜10个词条
     */
    @POST("Analysis/GetHotSearch")
    fun getHotSearch(): Observable<ArrayList<HotSearch>>

    /**
     * 查询关键字对应的商铺
     */
    @POST("ServiceProvider/QueryServiceProviderByServiceName")
    fun queryServiceProviderBySearceName(@Body bodySearch: BodySearch): Observable<ArrayList<ServiceProvider>>

    /**
     * 添加收藏的商铺
     */
    @POST("UserInfo/AddUserFavorite")
    fun addUserFavorite(@Body bodyForCollect: BodyForCollect): Observable<Any>


    /**
     * 取消收藏
     */
    @POST("UserInfo/RemoveUserFavorite")
    fun cancelCollect(@Body bodyForCancelCollect: BodyForCollect): Observable<Int>

    /**
     * 添加各个服务商的浏览量
     */
    @POST("ServiceProvider/AddViewingCountForServiceProvider")
    fun addViewingCountForServiceProvider(@Body bodyAddViewingCount: BodyAddViewingCount): Observable<Any>


    /**
     * 修改用户名
     */
    @POST("UserInfo/ModifyUserInfoByNickName")
    fun modifyUserInfoByNickName(@Body bodyUserName: BodyUserName): Observable<Any>

    /**
     * 修改用户头像
     */
    @POST("UserInfo/ModifyUserInfoByPhoto")
    fun modifyUserInfoByPhoto(@Body bodyPhoto: BodyPhoto): Observable<Any>

    /**
     *  发送短信验证码
     */
    @POST("Account/GetVerificationCode")
    fun sendSmsCode(@Body bodySendSmsCode: BodySendSmsCode): Observable<Int>


    /**
     * 获取上传七牛token
     */
    @POST("UserInfo/GetQiniuToken")
    fun getUpLoadToken(): Observable<String>

    /**
     * 检查是否收藏过
     */
    @POST("UserInfo/CheckIsMyFavotite")
    fun checkCollectForServiceProvider(@Body bodyForCollect: BodyForCollect): Observable<Int>

}