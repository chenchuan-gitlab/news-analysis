package com.news.analysis.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

public class ResponseBuilder implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timestamp;

    private String message;

    private int code;

    private Object data;

    public ResponseBuilder init(){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder;
    }

    public  ResponseBuilder success(){
        timestamp = new Date().getTime();
        this.message = "success";
        this.code = 1;
        return this;
    }

    public ResponseBuilder success(String message,int code){
        timestamp = new Date().getTime();
        this.message = message;
        this.code = code;
        return this;
    }

    public ResponseBuilder faild(){
        this.timestamp = new Date().getTime();
        this.message = "error";
        this.code = 0;
        return this;
    }

    public ResponseBuilder faild(String message,int code){
        timestamp = new Date().getTime();
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
