package com.pisces.android.wuha.function.search;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pisces.android.framworkerlibrary.utlis.SPUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2016/12/29 下午12:00.
 * Describe:
 */

public class SearchHistoryMenager {

    private static final String KEY_SEARCH_HISTORY = "KEY_SEARCH_HISTORY";

    public static void insertHistory(Context context, String contentName) {
        List<String> list = getHistory(context);
        if (list == null) list = new ArrayList<>();
        list.add(0, contentName);

        list = distinctd(list);


        if (list.size() > 10) list = list.subList(0, 10);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        String jsonStr = gson.toJson(list, listType);
        Log.d("jam", "==insertHistory===" + jsonStr);
        SPUtils.put(context, KEY_SEARCH_HISTORY, jsonStr);
    }


    /**
     * 数据去重
     *
     * @param list
     * @return
     */
    private static List<String> distinctd(List<String> list) {

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }


    public static List<String> getHistory(Context context) {

        String result = (String) SPUtils.get(context, KEY_SEARCH_HISTORY, "");
        Log.d("jam", "========getHistory=========" + result);
        if (TextUtils.isEmpty(result)) return null;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> list = gson.fromJson(result, listType);
        return list;
    }


    public static void clearnHistory(Context context){
        SPUtils.put(context, KEY_SEARCH_HISTORY, "");
    }

}
