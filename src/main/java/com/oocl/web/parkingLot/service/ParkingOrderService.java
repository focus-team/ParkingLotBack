package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.dto.OrderDTO;
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


    List<OrderDTO> getOrderDTOs();


}
