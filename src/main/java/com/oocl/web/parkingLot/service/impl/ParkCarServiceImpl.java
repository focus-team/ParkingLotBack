package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.common.StatusConst;
import com.oocl.web.parkingLot.common.TagConst;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkCarServiceImpl implements ParkCarService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    @Override
    public ResponseEntity park(Long userId) {
        Date date = new Date();
        ParkingOrder parkingOrder = new ParkingOrder("", new Date(System.currentTimeMillis()), date, 0, 0L, 0L, userId);

        ParkingOrder savedParkingOrder = parkingOrderRepository.save(parkingOrder);
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        savedParkingOrder.setOrderNum(yyyyMMdd + "." + savedParkingOrder.getId().toString());
        try {
            String tag = userRepository.findById(userId).get().getTag();
            List<ParkingBoy> tagBoysALl = parkingBoyRepository.findAll();
            List<ParkingBoy> tagBoysVIP = parkingBoyRepository.findAll().stream().filter(item -> item.getTag().endsWith(TagConst.VIP)).collect(Collectors.toList());

            List<ParkingBoy> tagBoys = tagBoysVIP.stream().filter(item -> item.getStatus().endsWith(StatusConst.FREE)).collect(Collectors.toList());

            for (ParkingBoy boy : tagBoys) {
                List<ParkingLot> parkingLots = boy.getParkingLots().stream().filter(itemLot -> itemLot.getRemine() > 1).collect(Collectors.toList());
                savedParkingOrder.setParkingBoyId(boy.getId());
                for (ParkingLot itemTag : parkingLots) {
                    itemTag.setRemine(itemTag.getRemine() - 1);
                    savedParkingOrder.setParkingLotId(itemTag.getId());

                    System.out.println("***********************before");
                    System.out.println(JSON.toJSONString(savedParkingOrder));

                    ParkingOrder save = parkingOrderRepository.save(savedParkingOrder);

                    System.out.println("***********************after");
                    System.out.println(JSON.toJSONString(save));

                    ParkingLot parkingLot = parkingLotRepository.findById(itemTag.getId()).get();
                    parkingLot.setRemine(parkingLot.getRemine() -1);
                    parkingLotRepository.save(parkingLot);
                    ParkingOrderVo parkingOrderVo = new ParkingOrderVo();
                    parkingOrderVo.setParkingOrder(save);

                    //username   userRepository.

                    parkingOrderVo.setUserName("user");
                    parkingOrderVo.setParkingLotName(parkingLot.getName());
                    parkingOrderVo.setParkingBoyName(boy.getName());

                    return ResponseEntity.ok().body(parkingOrderVo);
                }
            }
        } catch (Exception e) {
            throw new GlobalException(2, "There has no right parkingBoy!");
        }
        return null;
    }
}
