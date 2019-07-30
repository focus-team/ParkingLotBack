package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface ParkCarService {
    ResponseEntity park(Long userId, String startTime);
}
