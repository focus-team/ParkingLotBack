package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oocl.web.parkingLot.common.StatusConst;
import com.oocl.web.parkingLot.common.TagConst;
import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.entity.ParkingOrderVo;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.ParkCarService;
import com.oocl.web.parkingLot.service.ParkingCarStrategy;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParkCarServiceImpl implements ParkCarService {

    private ParkingOrderRepository parkingOrderRepository;
    private ParkingBoyRepository parkingBoyRepository;
    private UserRepository userRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingOrderService parkingOrderService;

    @Autowired
    public ParkCarServiceImpl(ParkingOrderRepository parkingOrderRepository, ParkingBoyRepository parkingBoyRepository, UserRepository userRepository, ParkingLotRepository parkingLotRepository,ParkingOrderService parkingOrderService) {
        this.parkingOrderRepository = parkingOrderRepository;
        this.parkingBoyRepository = parkingBoyRepository;
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingOrderService = parkingOrderService;
    }

    @Override
    public ResponseEntity park(Long userId,String startTime)throws Exception{

        ParkingOrder parkingOrderTemp = parkingOrderRepository.getParkingOrderByNotIsOverDateBOrderByUserId(userId);

        if(parkingOrderTemp != null){
            Map<Integer, String> data = new HashMap<Integer, String>();
            data.put(4, "The parkingBoy name has exited!");
            return  ResponseEntity.ok().body(new GlobalException(4, "The parkingBoy name has exited!",data));
        }

        ParkingCarStrategy parkingCarStrategyA = new ParkingCarStrategyA(parkingBoyRepository,userRepository,parkingLotRepository,parkingOrderRepository,parkingOrderService);
        return parkingCarStrategyA.ParkingCarStrategy(userId,startTime);
    }
}
