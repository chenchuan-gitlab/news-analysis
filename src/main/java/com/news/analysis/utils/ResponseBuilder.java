package com.news.analysis.utils;


import java.io.Serializable;

public class ResponseBuilder implements Serializable {
    private Long timestamp;

    private String message;

    private int code;

    private Object data;

    public static ResponseBuilder custom(){
        return new ResponseBuilder();
    }

    public  ResponseBuilder success(){
        timestamp = System.currentTimeMillis();
        this.message = "success";
        this.code = 1;
        return this;
    }

    public ResponseBuilder success(String message,int code){
        timestamp = System.currentTimeMillis();
        this.message = message;
        this.code = code;
        return this;
    }

    public ResponseBuilder faild(){
        this.timestamp = System.currentTimeMillis();
        this.message = "error";
        this.code = 0;
        return this;
    }

    public ResponseBuilder faild(String message,int code){
        timestamp = System.currentTimeMillis();
        this.message = message;
        this.code = code;
        return this;
    }

    public ResponseBuilder data(Object data){
        this.data = data;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
