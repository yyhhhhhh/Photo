package com.yyh.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yyh
 * @date 2022-04-12 16:03
 */
public class DateUtil {

    public static String formatDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
