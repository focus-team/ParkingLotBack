package com.oocl.web.parkingLot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:15:06
 * @description:
 */
@Data
@NoArgsConstructor
public class OrderDTO {

    @Id
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
    @Check(constraints = "cost > 0")
    private int cost;

    @NotNull
    @ApiModelProperty("停车员名字")
    private String parkingBoyName;

    @NotNull
    @ApiModelProperty("停车员电话")
    private String parkingBoyTel;

    @NotNull
    @ApiModelProperty("停车场名字")
    private String parkingLotName;

    @NotNull
    @ApiModelProperty("用户名字")
    private String userName;

    @ApiModelProperty("用户车牌号")
    private String userCarNo;

    @ApiModelProperty("用户手机号")
    private String userPhoneNo;

    @ApiModelProperty("是否过期")
    @JsonIgnore
    private int isOverDate;


    public OrderDTO(ParkingOrder parkingOrder){
        BeanUtils.copyProperties(parkingOrder,this);
    }


}
