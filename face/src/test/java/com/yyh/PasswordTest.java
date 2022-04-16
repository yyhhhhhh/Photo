package com.yyh;

import com.yyh.commons.utils.PasswordEncoderUtil;

/**
 * @author yyh
 * @date 2022-04-07 14:20
 */
public class PasswordTest {

    public static void main(String[] args) {
        String str = "admin";
        System.out.println(PasswordEncoderUtil.encode(str));
    }
}
