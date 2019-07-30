package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:15:15
 * @description:
 */

@Service
public interface FetchCarService {


    /**
     * 通过userID获取OrderDTO
     * @return
     */
    OrderDTO getOrderDTOByUserID(Long userID);


    /**
     *  通过订单预约取车
     *  1.取车默认是同一个停车员
     *  2.取车时间半小时内
     *  3.计费
     * @param orderDTO
     * @return
     */
    OrderDTO updateParkingOrder(OrderDTO orderDTO);






}
