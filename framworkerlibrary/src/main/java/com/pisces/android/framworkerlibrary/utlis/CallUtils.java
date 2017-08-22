package com.pisces.android.framworkerlibrary.utlis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Jam on 2016/4/28.
 */
public class CallUtils {

    /**
     * 直接拨号
     *
     * @param phoneNumber
     * @param activity
     */
    public static void callPhone(String phoneNumber, Activity activity) {
        Uri uri = Uri.parse("tel:" + phoneNumber.trim());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public static void callPhone(int phoneNumber, Activity activity) {
        String n = String.valueOf(phoneNumber);
        callPhone(n, activity);
    }

    /**
     * 跳转到拨号面板手动拨号
     *
     * @param phoneNumber
     * @param activity
     */
    public static void callPhoneDIAL(String phoneNumber, Activity activity) {
        Uri uri = Uri.parse("tel:" + phoneNumber.trim());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(uri);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void callPhoneDIAL(int phoneNumber, Activity activity) {
        String n = String.valueOf(phoneNumber);
        callPhoneDIAL(n, activity);
    }

}
