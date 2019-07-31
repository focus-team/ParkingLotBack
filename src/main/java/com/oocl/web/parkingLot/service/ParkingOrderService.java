package com.oocl.web.parkingLot.service;


import com.oocl.web.parkingLot.dto.OrderDetailDTO;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/30
 * @Time:14:34
 * @description:
 */
@Service
public interface ParkingOrderService {


    /**
     * 返回所有的订单信息列表(分页操作)
     * @return
     */
    List<OrderDetailDTO> getOrderDetailDTOs(int pageNum,int pageSize);


    /**
     * 通过id返回单个订单详情
     * @param orderId
     * @return
     */
    OrderDetailDTO getOrderDetailDTO(Long orderId);


    /**
     * 通过停车员ID和条件获取订单详情
     * @param parkingBoyId
     * @param condition
     * @return
     */
    List<OrderDetailDTO> getOrderDetailDTOsWithConditon(Long parkingBoyId,Long condition);


}
