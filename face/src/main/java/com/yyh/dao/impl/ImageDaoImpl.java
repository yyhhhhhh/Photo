package com.yyh.dao.impl;

import com.yyh.commons.utils.DateUtil;
import com.yyh.commons.utils.JdbcUtil;
import com.yyh.dao.ImageDao;
import com.yyh.entity.ImageFace;
import com.yyh.entity.ImageInfo;
import com.yyh.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yyh
 * @date 2022-04-12 15:33
 */
public class ImageDaoImpl implements ImageDao {

    private JdbcUtil util = new JdbcUtil();

    private PreparedStatement ps;

    private ResultSet rs;

    @Override
    public List<ImageInfo> selectPreUserPhotos(User user,int pageNum,int pageSize) {
        String sql = "select id,image_url,image_text,publish_time from " +
                "image_info where login = ? order by publish_time desc limit ?,?";
        List<ImageInfo> list = new ArrayList<>();
        try{
            ps = util.getPs(sql);
            ps.setObject(1,user.getLogin());
            ps.setObject(2,pageNum);
            ps.setObject(3,pageSize);
            rs = ps.executeQuery();
            while (rs.next()){
                ImageInfo ii = new ImageInfo();
                ii.setId(rs.getInt("id"));
                ii.setImageUrl(rs.getString("image_url"));
                ii.setImageText(rs.getString("image_text"));
                ii.setPublishTime(rs.getString("publish_time"));
                list.add(ii);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return list;
    }

    @Override
    public int insertImageInfo(ImageInfo imageInfo) {
        String sql = "insert into image_info values(null,?,?,?,?,6,?)";
        try{
            ps = util.getPs2(sql);
            ps.setObject(1,imageInfo.getLogin());
            ps.setObject(2,imageInfo.getImageUrl());
            ps.setObject(3,imageInfo.getImageText());
            ps.setObject(4,DateUtil.formatDateTime(new Date()));
            ps.setObject(5,imageInfo.getPublishStatus());
            int count = ps.executeUpdate();
            if(count > 0){
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return 0;
    }

    @Override
    public int insertImageFaceWithId(ImageFace imageFace, int id) {
        String sql = "insert into image_face values(null,?,?,?,?,?,?,?,?,?,?)";
        try{
            ps = util.getPs(sql);
            ps.setObject(1,imageFace.getFaceToken());
            ps.setObject(2,imageFace.getFaceAge());
            ps.setObject(3,imageFace.getFaceBeauty());
            ps.setObject(4,imageFace.getFaceType());
            ps.setObject(5,imageFace.getEmotion());
            ps.setObject(6,imageFace.getExpression());
            ps.setObject(7,imageFace.getGlasses());
            ps.setObject(8,imageFace.getGender());
            ps.setObject(9,imageFace.getFaceTime());
            ps.setObject(10,id);
            int count = ps.executeUpdate();
            if(count > 0){
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close();
        }
        return 0;
    }

    @Override
    public ImageInfo selectImageInfo(User user, int imageId) {
        String sql = "select id,image_url,image_text,publish_time from image_info where login = ? and id = ?";
        try{
            ps = util.getPs(sql);
            ps.setObject(1,user.getLogin());
            ps.setObject(2,imageId);
            rs = ps.executeQuery();
            while (rs.next()){
                ImageInfo ii = new ImageInfo();
                ii.setId(rs.getInt("id"));
                ii.setImageUrl(rs.getString("image_url"));
                ii.setImageText(rs.getString("image_text"));
                ii.setPublishTime(rs.getString("publish_time"));
                return ii;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return null;
    }

    @Override
    public ImageInfo selectImageInfoById(int imageId) {
        String sql = "select id,login from image_info where id = ?";
        try{
            ps = util.getPs(sql);
            ps.setObject(1,imageId);
            rs = ps.executeQuery();
            while (rs.next()){
                ImageInfo ii = new ImageInfo();
                ii.setId(rs.getInt("id"));
                ii.setLogin(rs.getString("login"));
                return ii;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return null;
    }

    @Override
    public List<ImageFace> selectImageFaceWithImageId(int id) {
        String sql = "select * from image_face where image_Id = ?";
        List<ImageFace> list = new ArrayList<>();
        try{
            ps = util.getPs(sql);
            ps.setObject(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                ImageFace ii = new ImageFace();
                ii.setId(rs.getInt("id"));
                ii.setFaceToken(rs.getString("face_token"));
                ii.setFaceAge(rs.getInt("face_age"));
                ii.setFaceBeauty(rs.getInt("face_beauty"));
                ii.setFaceType(rs.getString("face_type"));
                ii.setEmotion(rs.getString("emotion"));
                ii.setExpression(rs.getString("expression"));
                ii.setGlasses(rs.getString("glasses"));
                ii.setGender(rs.getString("gender"));
                ii.setFaceTime(rs.getString("face_time"));
                ii.setImageId(rs.getInt("image_id"));
                list.add(ii);
            }
            return list;
        }catch (Exception e){

        }finally {
            util.close(rs);
        }
        return null;
    }

    @Override
    public int deleteImageInfo(int id) {
        String sql = "delete from image_info where id = ?";
        try{
            ps = util.getPs(sql);
            ps.setObject(1,id);
            int count = ps.executeUpdate();
            if(count > 0){
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close();
        }
        return 0;
    }

    @Override
    public int deleteImageFaceWithImageId(int imageId) {
        String sql = "delete from image_face where image_id = ?";
        try{
            ps = util.getPs(sql);
            ps.setObject(1,imageId);
            int count = ps.executeUpdate();
            if(count > 0){
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close();
        }
        return 0;
    }

    @Override
    public int selectCountOfImage() {
        String sql = "select count(*) as 'total' from image_info";
        try{
            ps = util.getPs(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt("total");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            util.close(rs);
        }
        return 0;
    }
}
