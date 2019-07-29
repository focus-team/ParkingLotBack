package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.service.ParkingChargesStrategy;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:17:30
 * @description:
 *  具体实现计费A：
 *         1.半小时内免费停车
 *         2.不满一小时按一小时计算，
 *         3.普通每小时8元/vip每小时80元
 */
public class ConcreteParkingChargesA implements ParkingChargesStrategy {


    @Override
    public int ParkingCharges(ParkingOrder parkingOrder) {
        return 0;
    }


}
