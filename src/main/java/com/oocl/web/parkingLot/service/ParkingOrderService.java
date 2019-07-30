package com.oocl.web.parkingLot.service;


import com.oocl.web.parkingLot.dto.OrderDetailDTO;
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
     * 返回所有的订单信息(分页操作)
     * @return
     */
    List<OrderDetailDTO> getOrderDetailDTOs(int pageNum,int pageSize);


}
