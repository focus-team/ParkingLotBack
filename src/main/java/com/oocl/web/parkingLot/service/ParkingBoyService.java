package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface ParkingBoyService {
    ParkingBoy create(ParkingBoy parkingBoy);
}
