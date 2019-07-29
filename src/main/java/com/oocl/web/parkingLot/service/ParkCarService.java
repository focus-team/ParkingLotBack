package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkCarService {
    ResponseEntity park(Long userId);
}
