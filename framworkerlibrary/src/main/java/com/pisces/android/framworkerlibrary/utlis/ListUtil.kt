package com.yingwumeijia.commonlibrary.utils

/**
 * Created by Jam on 2017/3/3 下午2:38.
 * Describe:
 */

object ListUtil {


    fun isEmpty(list: List<*>?): Boolean {
        if (list == null) {
            return true
        } else if (list.isEmpty()) {
            return true
        } else {
            return false
        }
    }

    fun isEmpty(list: ArrayList<*>?):Boolean{
        if (list == null) {
            return true
        } else if (list.isEmpty()) {
            return true
        } else {
            return false
        }
    }
}
