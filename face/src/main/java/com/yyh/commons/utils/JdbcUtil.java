package com.yyh.commons.utils;

import java.sql.*;
import java.util.ResourceBundle;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * @author yyh
 * @date 2022-04-05 13:22
 */
public class JdbcUtil {

    public static final String DRIVER;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;

    Connection conn = null;
    PreparedStatement ps = null;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        DRIVER = bundle.getString("jdbc.driver");
        URL = bundle.getString("jdbc.url");
        USERNAME = bundle.getString("jdbc.username");
        PASSWORD = bundle.getString("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConn(){
        try{
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public PreparedStatement getPs(String sql){
        try {
            ps = getConn().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement getPs2(String sql){
        try {
            ps = getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(){
        if(null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs){
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                close();
            }
        }
    }

}
