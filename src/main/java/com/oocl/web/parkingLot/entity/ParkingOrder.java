package com.oocl.web.parkingLot.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Check;
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

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date endTime;

    @NotNull
    @ApiModelProperty("费用")
    @Check(constraints = "cost >= 0")
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

    @ApiModelProperty("是否过期")
    @JsonIgnore
    private int isOverDate;


    public ParkingOrder() {
    }

    public ParkingOrder(@NotNull Long userId) {
        this.userId = userId;
    }

    public ParkingOrder(@NotNull String orderNum, @NotNull Date startTime, Date endTime, @NotNull int cost, @NotNull Long parkingBoyId, @NotNull Long parkingLotId, @NotNull Long userId, int isOverDate) {
        this.orderNum = orderNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = 0;
        this.parkingBoyId = parkingBoyId;
        this.parkingLotId = parkingLotId;
        this.userId = userId;
        this.isOverDate = 0;
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
    public int getIsOverDate() {
        return isOverDate;
    }

    public void setIsOverDate(int isOverDate) {
        this.isOverDate = isOverDate;
    }

}
