package com.oocl.web.parkingLot.entity;


import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ParkingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty("订单号")
    private String orderNum;

    @NotNull
    @ApiModelProperty("开始时间")
    private Date startTime;

    @NotNull
    @ApiModelProperty("结束时间")
    private Date endTime;

    @NotNull
    @ApiModelProperty("费用")
    @Check(constraints = "cost > 0")
    private int cost;

    @NotNull
    @ApiModelProperty("停车员id")
    private Long parkingBoyId;

    @NotNull
    @ApiModelProperty("停车场id")
    private Long parkingLotId;

    @NotNull
    @ApiModelProperty("用户id")
    private Long userId;

    public ParkingOrder() {
    }

    public ParkingOrder(Long id, @NotNull String orderNum, @NotNull Date startTime, @NotNull Date endTime, @NotNull int cost, @NotNull Long parkingBoyId, @NotNull Long parkingLotId, @NotNull Long userId) {
        this.id = id;
        this.orderNum = orderNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.parkingBoyId = parkingBoyId;
        this.parkingLotId = parkingLotId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getParkingBoyId() {
        return parkingBoyId;
    }

    public void setParkingBoyId(Long parkingBoyId) {
        this.parkingBoyId = parkingBoyId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
