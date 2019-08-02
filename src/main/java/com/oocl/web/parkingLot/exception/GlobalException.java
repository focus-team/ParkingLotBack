package com.oocl.web.parkingLot.exception;

import java.util.HashMap;
import java.util.Map;

public class GlobalException extends RuntimeException {

    private Integer code;
    private String errMessage;
    Map<String, String> data = new HashMap<String, String>();


    public GlobalException(Integer code, String errMessage,Map<String, String> data) {

        this.code = code;
        this.errMessage = errMessage;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
