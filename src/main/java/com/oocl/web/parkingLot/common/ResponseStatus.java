package com.oocl.web.parkingLot.common;

public enum ResponseStatus {
    SUCCESS(200, "SUCCESS"),
    NOT_FOUND(404, "NOT_FOUND"),
    INVALID_USER(401, "INVALID_USER");

    int statusCode;
    String statusDesc;

    ResponseStatus(int statusCode, String statusDesc){
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
