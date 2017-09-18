package com.pisces.android.locationlibrary;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Chris Li on 2017/9/12.
 */

public class Constant {
    //广播  Action
    public static final String LOCATION_BROADCAST_ACTION = "LOCATION_BROADCAST_ACTION";
    private static SharedPreferences sp;
    public static final String C_LOCATION = "location";//地理位置
    public static String C_GPS_X = "gps_x";//地理经度
    public static String C_GPS_Y = "gps_y";//地理维度


    public static void setLocation(String s) {
        sp.edit().putString(C_LOCATION, s).commit();
    }

    public static String getLocation() {
        String s = sp.getString(C_LOCATION, "无");
        return s;
    }

    public static void setGpsX(double x) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(C_GPS_X, (float) x);
        editor.apply();
        editor.commit();
    }

    public static float getGpsX() {
        float x = sp.getFloat(C_GPS_X, 0);
        return x;
    }

    public static void setGpsY(double y) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(C_GPS_Y, (float) y);
        editor.apply();
        editor.commit();
    }

    public static float getGpsY() {
        float y = sp.getFloat(C_GPS_Y, 0);
        return y;
    }


    public static void init(Context context) {
        sp = context.getSharedPreferences("config", 0);
    }
}
