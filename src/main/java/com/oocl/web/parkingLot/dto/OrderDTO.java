package com.oocl.web.parkingLot.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import javax.persistence.Column;
import javax.persistence.Id;
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
    private Date startTime;

    @NotNull
    @ApiModelProperty("结束时间")
    private Date endTime;

    @NotNull
    @ApiModelProperty("费用")
    @Check(constraints = "cost > 0")
    private int cost;

    @NotNull
    @ApiModelProperty("停车员名字")
    private Long parkingBoyName;

    @NotNull
    @ApiModelProperty("停车员电话")
    private Long parkingBoyTel;


    @NotNull
    @ApiModelProperty("停车场名字")
    private Long parkingLotName;

    @NotNull
    @ApiModelProperty("用户名字")
    private Long userName;



}
