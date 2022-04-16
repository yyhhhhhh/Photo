package com.yyh.commons.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author yyh
 * @date 2022-04-07 14:12
 */
public class PasswordEncoderUtil {

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    public static String encode(String password){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            return bytes2Hex(md5.digest());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String bytes2Hex(byte[] bytes){
        char[] chs = new char[bytes.length*2];
        for(int i = 0,offset = 0;i<bytes.length;i++){
            chs[offset++] = HEX[bytes[i]>>4 & 0xf];
            chs[offset++] = HEX[bytes[i] & 0xf];
        }
        return new String(chs);
    }
}
