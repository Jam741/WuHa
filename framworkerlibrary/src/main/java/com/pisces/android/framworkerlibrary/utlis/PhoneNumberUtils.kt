package com.yingwumeijia.commonlibrary.utils

import android.text.TextUtils

/**
 * Created by Jam on 2016/4/20.
 */
object PhoneNumberUtils {

    /**
     * 验证手机格式

     * @param mobiles
     * *
     * @return
     */
    fun isMobile(mobiles: String?): Boolean {
        val telRegex = "[1][34578]\\d{9}"//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false
        else
            return mobiles!!.matches(telRegex.toRegex())
    }


    /**
     * 隐藏手机号码后四位
     * @param phone
     * *
     * @return
     */
    fun getCryptographicPhone(phone: String): String {
        //        phone2.substring(0,3) + "****" + phone2.substring(7, phone2.length())
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length)
    }

}
