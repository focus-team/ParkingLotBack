package com.oocl.web.parkingLot.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("用户Id,唯一标识键")
    private Long id;

    @NotNull
    @ApiModelProperty("用户名，非空")
    private String userName;

    @NotNull
    @ApiModelProperty("密码，非空")
    private String password;

    @NotNull
    @ApiModelProperty("用户类型。0 - 管理员; 1 - 普通客户; 2 - 停车员")
    private int type;

    @ApiModelProperty("普通用户标识。有普通、VIP跟黑卡用户三种")
    private String tag;

    @ApiModelProperty("当用户是车主时，需要具备的车牌号")
    private String carNo;

    @ApiModelProperty("当用户是车主时，需要具备的电话号码")
    private String phoneNo;

    public User() {
    }

    public User(Long id, @NotNull String userName, @NotNull String password, @NotNull int type, String tag, String carNo, String phoneNo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.tag = tag;
        this.carNo = carNo;
        this.phoneNo = phoneNo;
    }

    public User(@NotNull String userName, @NotNull String password, @NotNull int type, String tag, String carNo, String phoneNo) {
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.tag = tag;
        this.carNo = carNo;
        this.phoneNo = phoneNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
