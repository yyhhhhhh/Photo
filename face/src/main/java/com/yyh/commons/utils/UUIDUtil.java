package com.yyh.commons.utils;

import java.util.UUID;

/**
 * @author yyh
 * @date 2022-04-12 20:00
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,16);
    }
}
