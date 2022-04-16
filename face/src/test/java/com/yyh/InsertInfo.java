package com.yyh;

import com.yyh.commons.contants.Contants;
import com.yyh.commons.utils.JdbcUtil;
import com.yyh.commons.utils.PasswordEncoderUtil;

import java.sql.PreparedStatement;

/**
 * @author yyh
 * @date 2022-04-07 14:26
 */
public class InsertInfo {

    private static JdbcUtil jdbc = new JdbcUtil();

    public static void main(String[] args) {
        String sql = "insert into user_account(login,status,password) values(?,?,?)";
        try {
            PreparedStatement ps = jdbc.getPs(sql);
            ps.setObject(1,"210501110123");
            ps.setObject(2, Contants.ALLOW_LOGIN);
            ps.setObject(3, PasswordEncoderUtil.encode("123456"));
            int count = ps.executeUpdate();
            if(count > 0){
                System.out.println("添加成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
    }
}
