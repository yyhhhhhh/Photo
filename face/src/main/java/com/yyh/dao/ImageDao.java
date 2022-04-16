package com.yyh.dao;

import com.yyh.entity.ImageFace;
import com.yyh.entity.ImageInfo;
import com.yyh.entity.User;

import java.util.List;

/**
 * @author yyh
 * @date 2022-04-12 15:31
 */
public interface ImageDao {

    /**
     * 根据当前用户查询相册信息
     * @param user
     * @return
     */
    List<ImageInfo> selectPreUserPhotos(User user,int pageNum,int pageSize);

    /**
     * 根据图片信息插入数据
     * @param imageInfo
     * @return
     */
    int insertImageInfo(ImageInfo imageInfo);


    /**
     * 将人脸信息放入到数据库中
     * @param imageFace
     * @param id
     * @return
     */
    int insertImageFaceWithId(ImageFace imageFace, int id);

    /**
     * 根据用户和照片编号查询图片信息
     * @param user
     * @param imageId
     * @return
     */
    ImageInfo selectImageInfo(User user,int imageId);

    /**
     * 根据图片编号查询用户信息
     * @param imageId
     * @return
     */
    ImageInfo selectImageInfoById(int imageId);

    /**
     * 查询有人脸信息的
     * @param id
     * @return
     */
    List<ImageFace> selectImageFaceWithImageId(int id);

    /**
     * 删除照片
     * @param id
     * @return
     */
    int deleteImageInfo(int id);

    /**
     * 根据imageId删除人脸信息
     * @param imageId
     * @return
     */
    int deleteImageFaceWithImageId(int imageId);

    /**
     * 查询总照片数量
     * @return
     */
    int selectCountOfImage();
}
