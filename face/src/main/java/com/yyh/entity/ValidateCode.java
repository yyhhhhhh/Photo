package com.yyh.entity;

/**
 * @author yyh
 * @date 2022-03-31 11:02
 */
public class ValidateCode {

    private String randomCode;

    private String imageUrl;

    public ValidateCode() {
    }

    public ValidateCode(String randomCode,String imageUrl) {
        this.randomCode = randomCode;
        this.imageUrl = imageUrl;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
