package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingboy")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;

    @PostMapping
    private ResponseEntity add(@RequestBody ParkingBoy parkingBoy){
        ParkingBoy parkingBoySaved = parkingBoyService.create(parkingBoy);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingBoySaved);
    }
}
