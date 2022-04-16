package com.yyh.service;

import com.yyh.commons.contants.Contants;
import com.yyh.commons.entity.ReturnObject;
import com.yyh.entity.User;
import com.yyh.commons.utils.JdbcUtil;
import com.yyh.commons.utils.PasswordEncoderUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author yyh
 * @date 2022-04-07 14:47
 */
public class AccountService {

    private JdbcUtil jdbc = new JdbcUtil();

    public ReturnObject loginValidate(String login,String password){
        User user = getUserByLogin(login);
        ReturnObject returnObject = new ReturnObject();
        if( null == user){
            returnObject.setStatusCode(401);
            returnObject.setMessage("用户名或密码错误");
        }else{
            if(!Contants.ALLOW_LOGIN.equals(user.getStatus())){
                returnObject.setStatusCode(401);
                returnObject.setMessage("当前用户不可登录");
            }else if(user.getPassword().equals(PasswordEncoderUtil.encode(password))){
                returnObject.setStatusCode(200);
            }else{
                returnObject.setStatusCode(401);
                returnObject.setMessage("用户名或密码错误");
            }
        }
        return returnObject;
    }


    public User getUserByLogin(String login){
        String sql = "select login,status,password from user_account where login = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try{
            ps = jdbc.getPs(sql);
            ps.setObject(1,login);
            rs = ps.executeQuery();
            while (rs.next()){
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setStatus(rs.getString("status"));
                user.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
         e.printStackTrace();
        }finally {
            jdbc.close(rs);
        }
        return user;
    }
}
