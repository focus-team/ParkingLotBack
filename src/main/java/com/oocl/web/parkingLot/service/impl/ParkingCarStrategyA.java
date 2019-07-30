package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.ParkingCarStrategy;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:lanhusoft
 * Date:2019/7/30
 * Description:
 *            1、系统自动派单
 *            2、订单派给parkingBoy，该parkingBoy未完成订单数量不超过2，管理的停车场需要有空位
 */

@Component
public class ParkingCarStrategyA implements ParkingCarStrategy {




    private ParkingBoyRepository parkingBoyRepository;
    private UserRepository userRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingOrderRepository parkingOrderRepository;

    public ParkingCarStrategyA(ParkingBoyRepository parkingBoyRepository, UserRepository userRepository, ParkingLotRepository parkingLotRepository, ParkingOrderRepository parkingOrderRepository) {
        this.parkingBoyRepository = parkingBoyRepository;
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingOrderRepository = parkingOrderRepository;
    }

    @Override
    public ResponseEntity ParkingCarStrategy(Long userId, String startTime) throws Exception{

        ParkingOrder parkingOrder = new ParkingOrder("initNum", splitDateString(startTime),null, 0, 0L, 0L, userId,0);


        System.out.println(JSON.toJSONString(parkingOrder));

        ParkingOrder initParkingOrder = parkingOrderRepository.save(parkingOrder);

        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        initParkingOrder.setOrderNum(yyyyMMdd + "." + initParkingOrder.getId().toString());

        ParkingOrder savedParkingOrder = parkingOrderRepository.save(parkingOrder);

        System.out.println(JSON.toJSONString(savedParkingOrder));

        try {
            System.out.println(userId);
            List<ParkingBoy> availableBoys = getFilterParkingBoy(userId);
            if(availableBoys.size() == 0 ){
                return ResponseEntity.ok().body(new GlobalException(5, "AvailableParkingBoys has too many order pending.System " +
                        "can't dispatch!Waiting for pick-uping by hand!"));
            }

            for (ParkingBoy boy : availableBoys) {
                System.out.println(JSON.toJSONString(boy));
                List<ParkingLot> parkingLots = boy.getParkingLots().stream().filter(itemLot -> itemLot.getRemine() >= 1).collect(Collectors.toList());

                savedParkingOrder.setParkingBoyId(boy.getId());
                for (ParkingLot itemTag : parkingLots) {
                    itemTag.setRemine(itemTag.getRemine() - 1);
                    savedParkingOrder.setParkingLotId(itemTag.getId());

                    ParkingOrder save = parkingOrderRepository.save(savedParkingOrder);

                    ParkingLot parkingLot = changeParkingLotRemine(itemTag);

                    OrderDTO orderDto = createOrderDto(save, boy, parkingLot, userId);

                    return ResponseEntity.ok().body(orderDto);
                }
            }
        } catch (Exception e) {
            throw new GlobalException(2, "There has no right parkingBoy!");
        }
        parkingOrderRepository.save(savedParkingOrder);
        OrderDTO orderDTO = new OrderDTO(savedParkingOrder);
        return ResponseEntity.ok().body(orderDTO);
    }



    private Date splitDateString(String startTime){
        startTime = startTime.replace("?"," ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = null;
        try {
            startDate = sdf.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    private List<ParkingBoy> getFilterParkingBoy(Long userId){

        String tag = userRepository.findById(userId).get().getTag();
        List<ParkingBoy> tagBoys = parkingBoyRepository.findAll().stream().filter(item -> item.getTag().endsWith(tag)).collect(Collectors.toList());


        List<ParkingBoy> parkingBoysChargeParkingLotLessTwo = new ArrayList<>();
        for(ParkingBoy parkingBoy : tagBoys){
            List<ParkingOrder> parkingBoyUnfinishedOrders = parkingOrderRepository.getParkingOrderByNotIsOverDateOrderByParkingBoyId(parkingBoy.getId());
            if(parkingBoyUnfinishedOrders.size() < 2){
                parkingBoysChargeParkingLotLessTwo.add(parkingBoy);
            }
        }
        return parkingBoysChargeParkingLotLessTwo;
    }

    private ParkingLot changeParkingLotRemine(ParkingLot itemTag){
        ParkingLot parkingLot = parkingLotRepository.findById(itemTag.getId()).get();
        parkingLot.setRemine(parkingLot.getRemine() -1);
        parkingLotRepository.save(parkingLot);
        return parkingLot;
    }

    private OrderDTO createOrderDto(ParkingOrder save, ParkingBoy boy, ParkingLot parkingLot, Long userId){
        OrderDTO orderDTO = new OrderDTO(save);
        orderDTO.setParkingBoyName(boy.getName());
        orderDTO.setParkingBoyTel(boy.getPhone());
        orderDTO.setParkingLotName(parkingLot.getName());
        orderDTO.setUserName(userRepository.findById(userId).get().getUserName());
        return orderDTO;
    }

}
