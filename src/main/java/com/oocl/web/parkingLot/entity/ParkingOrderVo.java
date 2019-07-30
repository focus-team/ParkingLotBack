package com.oocl.web.parkingLot.entity;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:lanhusoft
 * Date:2019/7/29
 * Description:
 */

public class ParkingOrderVo {

    private ParkingOrder parkingOrder;

    private String parkingBoyName;

    private String parkingLotName;

    private String userName;

    public ParkingOrderVo() {
    }

    public ParkingOrderVo(ParkingOrder parkingOrder, String parkingBoyName, String parkingLotName, String userName) {
        this.parkingOrder = parkingOrder;
        this.parkingBoyName = parkingBoyName;
        this.parkingLotName = parkingLotName;
        this.userName = userName;
    }

    public ParkingOrder getParkingOrder() {
        return parkingOrder;
    }

    public void setParkingOrder(ParkingOrder parkingOrder) {
        this.parkingOrder = parkingOrder;
    }

    public String getParkingBoyName() {
        return parkingBoyName;
    }

    public void setParkingBoyName(String parkingBoyName) {
        this.parkingBoyName = parkingBoyName;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
