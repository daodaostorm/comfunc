package com.comfunmanager.utils;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SignUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);
	
    //解密密钥
    //测试密钥key
    public static final String key = "";
    //正式密钥key
    public static final String rekey = "";
    //测试向量iv
    public static final String initVector = "";
    //正式向量iv
    public static final String reinitVector = "";

    public static final String orgCode = "test_org2";
    
    //加密
    public static String Encrypt(String content, boolean isTest) throws Exception {
        try {
            IvParameterSpec iv;
            SecretKeySpec skeySpec;
            if (isTest) {
                    iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                    skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
                   
            } else {
            	 iv = new IvParameterSpec(reinitVector.getBytes("UTF-8"));
            	 skeySpec = new SecretKeySpec(rekey.getBytes("UTF-8"), "AES");
            }


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(content.getBytes());

            /*LOGGER.info("length: " + encrypted.length);
            
            for (int i = 0; i < encrypted.length; i++) {
            	LOGGER.info("char : " + Integer.toHexString(encrypted[i]));
            }*/
            
            return byte2HexStr(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encryptedStr, boolean isTest) {
        try {
            IvParameterSpec iv;
            SecretKeySpec skeySpec;
            if (isTest) {
                    iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                    skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
                   
            } else {
            	 iv = new IvParameterSpec(reinitVector.getBytes("UTF-8"));
            	 skeySpec = new SecretKeySpec(rekey.getBytes("UTF-8"), "AES");
            }

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] bytes = hexStr2Bytes(encryptedStr);
            byte[] original = cipher.doFinal(bytes);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static byte[] hexStr2Bytes(String hexStr) {
        hexStr = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
        int m = 0, n = 0;
        int iLen = hexStr.length() / 2;
        byte[] ret = new byte[iLen];

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + hexStr.substring(i * 2, m) + hexStr.substring(m, n)) & 0xFF);
        }
        return ret;
    }


    public static String byte2HexStr(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }

}
