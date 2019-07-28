package com.oocl.web.parkingLot.Response;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.web.bind.annotation.RequestBody;

public class ResponseBody  {

    private Integer status;

    private String msg;

    private ParkingBoy parkingBoy;

    public ResponseBody() {
    }

    public ResponseBody(Integer status, String msg, ParkingBoy parkingBoy) {
        this.status = status;
        this.msg = msg;
        this.parkingBoy = parkingBoy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }
}
