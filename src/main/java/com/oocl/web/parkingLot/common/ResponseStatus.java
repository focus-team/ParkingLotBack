package com.oocl.web.parkingLot.common;

public enum ResponseStatus {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    NOT_FOUND(404, "NOT_FOUND"),
    INVALID_USER(402, "INVALID_USER"),
    RELOGIN(401, "RELOGIN");

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
