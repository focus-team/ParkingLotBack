package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:32
 * @description:
 */

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    @Override
    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public List<ParkingLot> findParkingLots(){
        return parkingLotRepository.findAll();
    }


    @Override
    public ParkingLot updateParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }


    @Override
    public void deleteParkingLotById(Long id) {
        parkingLotRepository.deleteById(id);
    }




}
