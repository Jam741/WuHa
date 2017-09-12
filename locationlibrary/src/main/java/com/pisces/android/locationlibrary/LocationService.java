package com.pisces.android.locationlibrary;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;

/**
 * Created by Chris Li on 2017/9/12.
 */
public class LocationService extends APSService implements AMapLocationListener {

    private static final String TAG = "LocationService";
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private PendingIntent alarmPi = null;
    private AlarmManager alarm = null;
    private static final String LOCATION = "LOCATION";
    private String locationString = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            Message msg = mHandler.obtainMessage();
            msg.obj = aMapLocation;
            msg.what = 2;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        /*设置定位模式为高精度模式*/
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        /*设置定位监听*/
        locationClient.setLocationListener(this);

        /*创建Intent对象，action为LOCATION*/
        Intent alarmIntent = new Intent();
        alarmIntent.setAction(LOCATION);


        /*定义一个PendingIntent对象，PendingIntent。getBroadcast包含了sendBroadcast的动作，
        * 也就是发送了action为LOCATION的intent*/
        alarmPi = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        /*AlarmManager对象，注意这里并不是new一个对象，AlarmManager为系统级服务*/
        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        IntentFilter filter = new IntentFilter();
        filter.addAction(LOCATION);
        registerReceiver(alarmReceiver, filter);
        startService();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 启动定位
     */
    private void startService() {
        initOption();
        int alarmInterval = 60 * 3;//间隔时间
        String str = 60 * 3 + "";
        if (!TextUtils.isEmpty(str)) {
            alarmInterval = Integer.parseInt(str);
        }

        /*设置定位参数*/
        locationClient.setLocationOption(locationOption);
        /*启动定位*/
        locationClient.startLocation();
        mHandler.sendEmptyMessage(1);

        if (alarm != null) {
            /*设置一个闹钟，2秒后每隔一段时间执行启动一次定位程序*/
            alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 1000 * 10, alarmInterval * 1000, alarmPi);
        }
    }

    /**
     * 停止定位
     */
    private void stopService() {
        /*停止定位*/
        locationClient.stopLocation();
        mHandler.sendEmptyMessage(3);
        /*停止定位的时候取消闹钟*/
        if (alarm != null) {
            alarm.cancel(alarmPi);
        }
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService();
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }

        if (null != alarmReceiver) {
            unregisterReceiver(alarmReceiver);
            alarmReceiver = null;
        }
    }

    /**
     * 设置定位参数
     */
    private void initOption() {
        /*设置是否需要显示地址信息*/
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        String strInterval;
        strInterval = "" + 1000 * 2;

        if (!TextUtils.isEmpty(strInterval)) {
            /*设置发送定位请求的时间间隔，最小为1000，如果小于1000，按1000算*/
            locationOption.setInterval(Long.valueOf(strInterval));
        }

    }


    /**
     * 广播接收者
     */
    private BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LOCATION)) {
                if (locationClient != null) {
                    locationClient.startLocation();
                }
            }
        }
    };

    /**
     * 异步处理器
     */
    Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case 1://开始定位
                    Log.i(TAG, "正在定位...");
                    break;
                case 2://定位成功
                    AMapLocation loc = (AMapLocation) msg.obj;
                    boolean b = GetLocationStr(loc);
                    if (b) {
                        Log.i(TAG, "定位成功\n" + locationString);
                    } else {
                        Log.i(TAG, "定位失败\n" + locationString);

                    }

                    break;
                case 3://定位结束
                    Log.i(TAG, "定位结束");
                    break;
                default:
                    break;

            }
        }
    };

    private boolean GetLocationStr(AMapLocation location) {
        if (null == location) {
            return false;
        }
        StringBuffer sb = new StringBuffer();
        if (location.getErrorCode() == 0) {
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            sb.append("省            : " + location.getProvince() + "\n");
            sb.append("市            : " + location.getCity() + "\n");
            sb.append("地    址    : " + location.getAddress() + "\n");
            //定位完成的时间
            sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
            //定位之后的回调时间
            sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
            locationString = sb.toString();
            return true;
        } else {
            //定位失败
            sb.append("错误码:" + location.getErrorCode() + "\n");
            sb.append("错误信息:" + location.getErrorInfo() + "\n");
            sb.append("错误描述:" + location.getLocationDetail() + "\n");
            //定位之后的回调时间
            sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
            locationString = sb.toString();
            return false;
        }

    }


}
