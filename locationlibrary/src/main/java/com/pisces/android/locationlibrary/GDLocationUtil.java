package com.pisces.android.locationlibrary;

import android.content.Context;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by Chris Li on 2017/9/12.
 */

public class GDLocationUtil {
    private static AMapLocationClient mLocationClient;
    private static AMapLocationClientOption mLocationOption = null;
    private static AMapLocation sLocation = null;

    /**
     * 初始化地图导航，只需调用一次
     */
    public static void init(Context context) {
        // 声明mLocationOption对象
        mLocationClient = new AMapLocationClient(context);
        // 初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mLocationOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mLocationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mLocationOption.setInterval(2000);// 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mLocationOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mLocationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mLocationOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mLocationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mLocationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        // 设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
    }

    /**
     * 定位结果回调
     */
    public interface MyLocationListener {
        /*成功*/
        void result(AMapLocation location);

        /*失败*/
        void fail(String message);
    }

    /**
     * 获取位置，如果之前获取过定位结果，则不会重复获取
     */
    public static void getLocation(MyLocationListener listener) {
        if (sLocation == null) {
            getCurrentLocation(listener);
        } else {
            listener.result(sLocation);
        }
    }

    /**
     * 获取位置，重新发起获取位置请求
     */
    public static void getCurrentLocation(final MyLocationListener listener) {
        if (mLocationClient == null) {
            listener.fail("定位失败");
            return;
        }
        // 设置定位监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    //定位成功，取消定位
                    mLocationClient.stopLocation();
                    sLocation = location;
                    listener.result(location);
                } else {
                    //获取定位数据失败
                    listener.fail("定位失败");
                }
            }
        });
        // 启动定位
        mLocationClient.startLocation();
    }

    /**
     * 销毁定位，必须在退出程序时调用，否则定位会发生异常
     */
    public static void destroy() {
        mLocationClient.onDestroy();
    }
}
