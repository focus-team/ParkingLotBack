package com.oocl.web.parkingLot.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:lanhusoft
 * Date:7/29/2019
 * Description:
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    //使之不在Json序列化的结果当中
    public boolean isSuccess(){
        return this.status == ResponseStatus.SUCCESS.statusCode;
    }
    public int getStatus(){
        return status;
    }
    public String getMsg(){
        return msg;
    }
    public T getData(){
        return data;
    }


    /*
     * 下面这些泛型方法，作用是根据传入的类型的不同，返回不同类型的ServerResponse
     * */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatusCode(),ResponseStatus.SUCCESS.statusDesc);
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatusCode(),msg);
    }
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatusCode(),data);
    }
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatusCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseStatus.ERROR.getStatusCode(),ResponseStatus.ERROR.getStatusDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseStatus.ERROR.getStatusCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeAndMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
