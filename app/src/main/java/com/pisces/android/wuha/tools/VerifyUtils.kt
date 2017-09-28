package com.yingwumeijia.baseywmj.utils

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * Created by jamisonline on 2017/6/16.
 *
 * 校验文本规则工具类
 */
object VerifyUtils {

    val PASSWORD_LENGTH_MINI = 8
    val PASSWORD_LENGTH_MAX = 20


    /**
     * 校验密码
     */
    fun verifyPassword(password: String?): Boolean {
        if (TextUtils.isEmpty(password))
            return false
        else
            return password!!.trim().length >= PASSWORD_LENGTH_MINI && password.length <= PASSWORD_LENGTH_MAX

    }


    val NIKENAME_LENGTH_MINI = 1
    val NIKENAME_LENGTH_MAX = 15


    /**
     * 校验昵称
     */
    fun verifyNikeName(nikeName: String?): Boolean {
        if (TextUtils.isEmpty(nikeName))
            return false
        else return nikeName!!.trim().length >= NIKENAME_LENGTH_MINI && nikeName.trim().length <= NIKENAME_LENGTH_MAX
    }

    val SMS_CODE_LENGTH = 6

    /**
     * 校验短信验证码
     */
    fun verifySmsCode(smsCode: String?): Boolean {
        if (TextUtils.isEmpty(smsCode))
            return false
        else
            return smsCode!!.trim().length == SMS_CODE_LENGTH
    }


    /**
     * 校验手机号码
     */
    fun verifyMobilePhoneNumber(mobiles: String?): Boolean {
        val telRegex = "[1][34578]\\d{9}"//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false
        else
            return mobiles!!.matches(telRegex.toRegex())
    }


    /**
     * 校验公司名称
     */
    fun verifyCompanyName(companyName: String?): Boolean {
        if (TextUtils.isEmpty(companyName)) return false
        else return companyName!!.trim().length >= 2
    }


    /**
     * 验证用户名只包含字母，数字，中文
     * @param account
     * @return
     */
    fun verifyUsername(username: String?): Boolean {

        if (TextUtils.isEmpty(username)){
            return false
        }

        if (username!!.length < 2 || username!!.length > 16) {
            return false
        } else {

            var value = username.replace("-", "")
            value = value.replace("_", "")
            value = value.replace(".", "")

            // 验证用户名只包含字母，数字，中文
            val all = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$"
            val p = Pattern.compile(all)
            val m = p.matcher(value)
            return m.matches()

        }

    }
}

