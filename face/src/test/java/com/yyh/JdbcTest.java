package com.yyh;

import com.yyh.dao.ImageDao;
import com.yyh.dao.impl.ImageDaoImpl;
import com.yyh.entity.ImageFace;
import com.yyh.entity.ImageInfo;
import com.yyh.entity.User;
import com.yyh.service.ImageService;
import org.junit.Test;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author yyh
 * @date 2022-04-05 11:32
 */
public class JdbcTest {

    @Test
    public void testResult(){
        System.out.println(Integer.parseInt(null));
    }

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("jdbc.driver");
        String url = bundle.getString("jdbc.url");
        String username = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            String sql = "SELECT a.movie_id,a.name,a.name_en,img,rating,story,length,movie_publish_date,movie_year FROM " +
                    "movie_info a,movie_types b,type_info c WHERE " +
                    "a.movie_id = b.movie_id AND b.type_id = c.type_id AND c.NAME=? AND " +
                    "a.movie_year >= ? AND a.rating > ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"喜剧");
            ps.setObject(2,2000);
            ps.setObject(3,8.0f);
            rs = ps.executeQuery();
            int row = 1;
            while (rs.next()){
                System.out.println("当前第"+ row++ +"行");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库连接失败"+e.getMessage());
        }finally {
            if(null != rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != ps){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
