package com.oocl.web.parkingLot.dto;

import com.oocl.web.parkingLot.common.OrderStatusConst;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/30
 * @Time:15:01
 * @description:
 */

@Data
@NoArgsConstructor
public class OrderDetailsDTO extends OrderDTO{

    @ApiModelProperty("订单状态：'已完成'，'未接单'，'已预约' ")
    private String State;


    public OrderDetailsDTO(ParkingOrder parkingOrder) {
        BeanUtils.copyProperties(parkingOrder,this);
    }

    private void judgeState(){

        if(this.getEndTime() != null){
            this.State = OrderStatusConst.FINISHED;
        }else {
            if(this.getIsOverDate() == 1){
                this.State = OrderStatusConst.SUBSCRIBED;
            }else {
                this.State = OrderStatusConst.UNHANDLED;
            }
        }

    }



}
