package com.yyh.service;

import com.yyh.entity.User;

/**
 * @author yyh
 * @date 2022-03-30 21:30
 */
public class UserTokenService {

    private AccountService service = new AccountService();

    public String getToken(String login){
        return "thisisatoken."+login+".tokenend";
    }

    public User getUser(String token){
        User user = null;
        try{
            user = service.getUserByLogin(token.split("\\.")[1]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
