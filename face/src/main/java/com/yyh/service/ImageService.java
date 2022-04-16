package com.yyh.service;

import com.yyh.dao.ImageDao;
import com.yyh.dao.impl.ImageDaoImpl;
import com.yyh.entity.ImageFace;
import com.yyh.entity.ImageInfo;
import com.yyh.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-04-12 15:28
 */
public class ImageService {

    private ImageDao imageDao = new ImageDaoImpl();

    /**
     * 查询当前用户的相册
     * @param user
     * @return
     */
    public List<ImageInfo> queryPreUserPhotos(User user,int pageNum,int pageSize){
        return imageDao.selectPreUserPhotos(user,pageNum,pageSize);
    }

    /**
     * 查询所有图片数量
     * @return
     */
    public int queryCountOfImage(){
        return imageDao.selectCountOfImage();
    }

    /**
     * 根据数据库返回的信息获取插入成功数据的id
     * @return
     */
    public int getImageInfoId(ImageInfo imageInfo){
        int flag = imageDao.insertImageInfo(imageInfo);
        if(flag > 0){
            return flag;
        }
        return 0;
    }

    /**
     * 将带有人脸的图片的人脸信息放入到数据库中
     * @param list
     * @param id
     */
    public void saveImageFaceWithId(List<ImageFace> list,int id){
        list.forEach(imageFace -> {
            imageDao.insertImageFaceWithId(imageFace,id);
        });
    }

    /**
     * 根据用户和图片id查询单个图片信息
     * @param user
     * @param imageId
     * @return
     */
    public ImageInfo queryImageInfoAndImageFace(User user,int imageId){
        ImageInfo imageInfo = imageDao.selectImageInfo(user, imageId);
        List<ImageFace> list = imageDao.selectImageFaceWithImageId(imageInfo.getId());
        if(null == list){
            return imageInfo;
        }
        imageInfo.setImageFaceList(list);
        return imageInfo;
    }

    /**
     * 判断数据库中的数据和token的是否相同
     * 相同继续判断图片是否包含人脸信息,包含就删掉图片和图片所包含的人脸信息,否则删除图片
     * @return
     */
    public int updateImageInfoAndFace(User user,int imageId){
        ImageInfo imageInfo = imageDao.selectImageInfoById(imageId);
        if(!user.getLogin().equals(imageInfo.getLogin()))
            return 0;
        List<ImageFace> list = imageDao.selectImageFaceWithImageId(imageInfo.getId());
        if(null == list){
            imageDao.deleteImageInfo(imageInfo.getId());
            return 1;
        }
        imageDao.deleteImageFaceWithImageId(imageInfo.getId());
        imageDao.deleteImageInfo(imageInfo.getId());
        return 1;
    }
}
