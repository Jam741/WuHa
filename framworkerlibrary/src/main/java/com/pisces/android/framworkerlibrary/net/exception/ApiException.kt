package com.pisces.android.framworkerlibrary.net.exception

/**
 * Created by Jam on 2017/2/17 下午5:38.
 * Describe:
 */

class ApiException(var error_codel: Int, var error_message: String?, var debug_message: String?) : RuntimeException()
