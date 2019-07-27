package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingBoyServiceImpl implements ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Override
    public ParkingBoy create(ParkingBoy parkingBoy) {
        return parkingBoyRepository.save(parkingBoy);
    }
}
