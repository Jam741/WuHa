package com.pisces.android.framworkerlibrary.utlis;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jam on 2016/11/29 上午10:00.
 * Describe:
 */

public class FromartDateUtil {


    public static String fromartDateYMdHm(long times) {
        Log.d("jam", "=======" + times);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String date = sdf.format(new Date(times));

//        System.out.println(times);
//        Date date = new Date(times);
//        String tim = sdf.format(date);
//        System.out.println(tim);
        return date;
    }

    public static String fromartDateYMdHm(String times) {

        Log.d("jam", "=======" + times);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String date = sdf.format(new Date(Long.valueOf(times)));

//        System.out.println(times);
//        Date date = new Date(times);
//        String tim = sdf.format(date);
//        System.out.println(tim);
        return date;
    }

    public static String fromartDateYMdHmSs(String times) {

        Log.d("jam", "=======" + times);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String date = sdf.format(new Date(Long.valueOf(times)));

//        System.out.println(times);
//        Date date = new Date(times);
//        String tim = sdf.format(date);
//        System.out.println(tim);
        return date;
    }

    public static String fromartDateYMd(long times) {
        Log.d("jam", "=======" + times);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String date = sdf.format(new Date(times));

//        System.out.println(times);
//        Date date = new Date(times);
//        String tim = sdf.format(date);
//        System.out.println(tim);
        return date;
    }

    public static String fromartDateYMd(String times) {
        Log.d("jam", "=======" + times);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String date = sdf.format(new Date(Long.valueOf(times)));

//        System.out.println(times);
//        Date date = new Date(times);
//        String tim = sdf.format(date);
//        System.out.println(tim);
        return date;
    }
}
