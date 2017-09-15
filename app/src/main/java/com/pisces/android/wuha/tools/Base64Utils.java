/*
 * Copyright (c) 2016 ywmj.com. All Rights Reserved.
 */
package com.pisces.android.wuha.tools;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64Utils
 *
 * @author cc
 * @version 1.0, 2016/10/24
 */
public class Base64Utils {

    /**
     * Base64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * Base64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
