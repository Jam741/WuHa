package com.pisces.android.wuha.tools;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Jam on 2016/11/11 上午10:46.
 * Describe:
 */

public class RSAUtils {

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7/iS3ZkZiuj0Tzx3G3yTUmdSrxqLfjqLOGZWfiegY5WjfnNno5WBGugarFGAgOIA39r+GC/52QTxGgKjgxJ3DHLWf9JtYCZE9YIXtVJmnlwdndI7T9468PlWHVplCeUgYq1jbItE29IJ2q4EUxnCOMQFC5Ylr2LRENEnjr0WdOwIDAQAB";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "MIICXAIBAAKBgQC7/iS3ZkZiuj0Tzx3G3yTUmdSrxqLfjqLOGZWfiegY5WjfnNno5WBGugarFGAgOIA39r+GC/52QTxGgKjgxJ3DHLWf9JtYCZE9YIXtVJmnlwdndI7T9468PlWHVplCeUgYq1jbItE29IJ2q4EUxnCOMQFC5Ylr2LRENEnjr0WdOwIDAQABAoGAL1YA/MUd+AIZGwHN56OMbJQHfvFXVZ9e0zKSAEgDTzGExLmEDSakpWp1/2H0CmjvsCfLdf9TJYerm70NyPr5FheIow/vjoFDwDm5WzFmCDCFH5fskIbAqNrS4wH91lq6WXla7Nr8ns+jjQ32yzCIgqYRSLMDfbjqy4I3cfOzrTECQQDe1OhCQlLrzl5v8Qu+a/Dqu7hutzU7QpbSg5j6f6bc9QLHRb/DfVbT42nT8J3pH/j9bpRw3ZutxDbnSoMOH2bNAkEA1/mvR5NntLONOVhlcA8YxTh5+3IoAe8CteJopxvt8kHU5ReiLx7asZpK1zHA342n3tctGfpEkq60FXH8pOjEJwJADOq8l/KuSdsJoGWRr7UkHwdItqpKHKhMg+F7AbJaot5VDeYeKp/eY6QAI3gEP1pKHa7GThCakKUaJagtFql9VQJBAJB/9c4lyZUVLL/ZbMT01NXfW33oeuwQRff7a5mjDiiv8wj0Lwbn1dpOKRShrTHlTRPDU+G1mHurd8GBak/LjLECQGN0xN2mU0tQ4iPn+f/JEvZXdlNi4IlCf12Z/XPd+FI3IprtjsGsQy5rH95MS5XBN+ZziwKsbbnV+NHG4oQkFhU=";

    public static void main(String[] args) throws Exception {

        String source = "你好nihao";// 要加密的字符串
        System.out.println("准备用公钥加密的字符串为：" + source);

        String cryptograph = encrypt(source);// 生成的密文
        System.out.print("用公钥加密后的结果为:" + cryptograph);
        System.out.println();

        String target = decrypt(cryptograph);// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
    }

    /**
     * 加密方法
     *
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {

        Key publicKey = getPubKey();

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密算法
     *
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception {

        Key privateKey = getPrivateKey();

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);

        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    private static Key getKey(String fileName) throws Exception, IOException {
        Key key;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(fileName));


            key = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        } finally {
            ois.close();
        }
        return key;
    }




    /**
     * 实例化公钥
     *
     * @return
     */
    private static PublicKey getPubKey() {
        PublicKey publicKey = null;
        try {
            // 自己的公钥(测试)
            String pubKey = PUBLIC_KEY_FILE;
            java.security.spec.X509EncodedKeySpec bobPubKeySpec = new java.security.spec.X509EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(pubKey));
            // RSA对称加密算法
            java.security.KeyFactory keyFactory;
            keyFactory = java.security.KeyFactory.getInstance("RSA");
            // 取公钥匙对象
            publicKey = keyFactory.generatePublic(bobPubKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicKey;
    }


    /**
     * 实例化私钥
     *
     * @return
     */
    private static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        String priKey = PRIVATE_KEY_FILE;
        PKCS8EncodedKeySpec priPKCS8;
        try {
            priPKCS8 = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(priKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            privateKey = keyf.generatePrivate(priPKCS8);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

}
