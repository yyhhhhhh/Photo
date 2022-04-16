package com.yyh.entity;

/**
 * @author yyh
 * @date 2022-04-12 11:04
 */
public class ImageFace {

    private Integer id;

    private String faceToken;

    private Integer faceAge;

    private Integer faceBeauty;

    private String faceType;

    private String emotion;

    private String expression;

    private String glasses;

    private String gender;

    private String faceTime;

    private Integer imageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public Integer getFaceAge() {
        return faceAge;
    }

    public void setFaceAge(Integer faceAge) {
        this.faceAge = faceAge;
    }

    public Integer getFaceBeauty() {
        return faceBeauty;
    }

    public void setFaceBeauty(Integer faceBeauty) {
        this.faceBeauty = faceBeauty;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(String faceTime) {
        this.faceTime = faceTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ImageFace{" +
                "id=" + id +
                ", faceToken='" + faceToken + '\'' +
                ", faceAge=" + faceAge +
                ", faceBeauty=" + faceBeauty +
                ", faceType='" + faceType + '\'' +
                ", emotion='" + emotion + '\'' +
                ", expression='" + expression + '\'' +
                ", glasses='" + glasses + '\'' +
                ", gender='" + gender + '\'' +
                ", faceTime='" + faceTime + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
