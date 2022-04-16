package com.yyh;

import com.yyh.commons.entity.ReturnObject;
import com.yyh.service.AccountService;

/**
 * @author yyh
 * @date 2022-04-07 14:57
 */
public class LoginTest {

    public static void main(String[] args) {
        AccountService service = new AccountService();
        ReturnObject returnObject = service.loginValidate("210501110123", "12233456");
        System.out.println(returnObject);
    }
}
