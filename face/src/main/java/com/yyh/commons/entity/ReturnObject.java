package com.yyh.commons.entity;

/**
 * @author yyh
 * @date 2022-03-30 22:06
 */
public class ReturnObject {

    private Integer statusCode;
    private String message;
    private Object retObject;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetObject() {
        return retObject;
    }

    public void setRetObject(Object retObject) {
        this.retObject = retObject;
    }

    @Override
    public String toString() {
        return "ReturnObject{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", retObject=" + retObject +
                '}';
    }
}
