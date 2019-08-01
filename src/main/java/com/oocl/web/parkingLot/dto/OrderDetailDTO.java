package com.oocl.web.parkingLot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.parkingLot.common.OrderStatusConst;
import com.oocl.web.parkingLot.common.OrderStatusEnum;
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
public class OrderDetailDTO extends OrderDTO {

    @ApiModelProperty("订单状态：'已完成'，'未接单'，'已预约' ")
    private String State;

    @JsonIgnore
    private int status_code;


    public OrderDetailDTO(OrderDTO orderDTO) {

        BeanUtils.copyProperties(orderDTO, this);
        judgeState();

    }

    private void judgeState() {

        if(this.getParkingBoyName().equals("")  || this.getParkingBoyName() == null) {

            this.State = OrderStatusConst.UNHANDLED;

        }else {

            if (this.getIsOverDate() == 0) {
                this.State = OrderStatusConst.SUBSCRIBED;
            } else {
                this.State = OrderStatusConst.FINISHED;
            }

        }

        status_code = OrderStatusEnum.getByStatusCode(this.State);

    }


}
