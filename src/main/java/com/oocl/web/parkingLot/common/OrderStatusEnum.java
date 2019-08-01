package com.oocl.web.parkingLot.common;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/8/1
 * @Time:02:20
 * @description:
 */
public enum OrderStatusEnum {


    ORDER_FINISHED("已完成", 2),
    ORDER_SUBSCRIBED("已预约", 1),
    ORDER_UNHANDLED("未处理", 0);

    String status;
    int status_code;


    OrderStatusEnum(String status, int status_code) {
        this.status = status;
        this.status_code = status_code;
    }

    public static int getByStatusCode(String status) {
        for (OrderStatusEnum orderStatusEnum : values()) {
            if (orderStatusEnum.status.equals(status)) {
                return orderStatusEnum.status_code;
            }
        }
        return -1;
    }


}
