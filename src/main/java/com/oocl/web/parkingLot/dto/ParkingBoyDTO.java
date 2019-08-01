package com.oocl.web.parkingLot.dto;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:Anderson
 * Date:2019/8/1
 * Description:
 */

public class ParkingBoyDTO {

    @Id
    private Long id;

    @NotNull
    @ApiModelProperty("停车员编号")
    private String name;

    @NotNull
    @ApiModelProperty("停车员电话")
    private String phone;

    @NotNull
    @ApiModelProperty("停车员年龄")
    private Integer age;

    @NotNull
    @ApiModelProperty("停车员性别")
    private String sex;

    @NotNull
    @ApiModelProperty("接收已预约单数")
    private Integer bookedOrderSum;

    @NotNull
    @ApiModelProperty("停车员性标签")
    private String tag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getBookedOrderSum() {
        return bookedOrderSum;
    }

    public void setBookedOrderSum(Integer bookedOrderSum) {
        this.bookedOrderSum = bookedOrderSum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ParkingBoyDTO(ParkingBoy parkingBoy){
        BeanUtils.copyProperties(parkingBoy,this);
    }

}
