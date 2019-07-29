package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;

import java.util.Date;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:17:27
 * @description: 根据订单计算费用的策略接口
 */
public interface ParkingChargesStrategy {


    int ParkingCharges(Date start, Date end, ParkingLot parkingLot);


}
