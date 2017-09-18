package com.yingwumeijia.commonlibrary.utils

/**
 * Created by Jam on 2017/3/3 下午2:38.
 * Describe:
 */

object ListUtil {


    fun isEmpty(list: List<*>?): Boolean {
        return when {
            list == null -> true
            list.isEmpty() -> true
            else -> false
        }
    }

    fun isEmpty(list: ArrayList<*>?):Boolean{
        return when {
            list == null -> true
            list.isEmpty() -> true
            else -> false
        }
    }
}
