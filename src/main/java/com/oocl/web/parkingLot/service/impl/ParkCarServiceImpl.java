package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
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


    @Override
    public ParkingOrder create(Long userId) {
        Date date = new Date();
        System.out.println(date.getTime());
        ParkingOrder parkingOrder = new ParkingOrder("", new Date(System.currentTimeMillis()), date, 0, 0L, 0L, userId);

        ParkingOrder savedParkingOrder = parkingOrderRepository.save(parkingOrder);

        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        savedParkingOrder.setOrderNum(yyyyMMdd + "." + savedParkingOrder.getId().toString());

        try {
            List<ParkingBoy> tagBoys = parkingBoyRepository.findAll().stream().filter(item -> item.getTag().equals("VIP") && item.getStatus().equals("free")).collect(Collectors.toList());

//            tagBoys.forEach(
            for (ParkingBoy boy : tagBoys) {

                List<ParkingLot> parkingLots = boy.getParkingLots().stream().filter(itemLot -> itemLot.getRemine() > 1).collect(Collectors.toList());
                savedParkingOrder.setParkingBoyId(boy.getId());
                for (ParkingLot itemTag : parkingLots) {
                    itemTag.setRemine(itemTag.getRemine() - 1);
                    savedParkingOrder.setParkingLotId(itemTag.getId());
                    ParkingOrder save = parkingOrderRepository.save(savedParkingOrder);
                    return save;
                }
            }
        } catch (Exception e) {
            throw new GlobalException(2, "There has no right parkingBoy!");
        }
        return null;
    }
}
