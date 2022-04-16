package com.yyh.entity;

import java.util.List;

/**
 * @author yyh
 * @date 2022-04-12 11:01
 */
public class ImageInfo {

    private Integer id;

    private String login;

    private String imageUrl;

    private String imageText;

    private String publishTime;

    private Integer imageSize;

    private String publishStatus;

    private List<ImageFace> imageFaceList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public List<ImageFace> getImageFaceList() {
        return imageFaceList;
    }

    public void setImageFaceList(List<ImageFace> imageFaceList) {
        this.imageFaceList = imageFaceList;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageText='" + imageText + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", imageSize=" + imageSize +
                ", publishStatus='" + publishStatus + '\'' +
                '}';
    }
}
