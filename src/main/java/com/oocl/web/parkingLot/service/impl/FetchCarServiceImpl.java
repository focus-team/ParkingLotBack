package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.FetchCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:15:16
 * @description:
 */

@Service
public class FetchCarServiceImpl implements FetchCarService {


    private ParkingLotRepository parkingLotRepository;
    private ParkingBoyRepository parkingBoyRepository;
    private UserRepository userRepository;

    @Autowired
    public FetchCarServiceImpl(ParkingLotRepository parkingLotRepository, ParkingBoyRepository parkingBoyRepository, UserRepository userRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingBoyRepository = parkingBoyRepository;
        this.userRepository = userRepository;
    }

    /**
     * 通过订单预约取车
     * 1.取车默认是同一个停车员
     * 2.取车时间半小时内
     * 3.计费
     *
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO uodateParkingOrder(OrderDTO orderDTO) {

        //通过停车场名字找停车场,剩余空位+1




        return null;

    }
}



