package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkinglots")
@CrossOrigin(origins = "*")
public class ParkingLotControllerTest {


    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ParkingLot saveParkinglot(ParkingLot parkingLot){

        return parkingLotService.saveParkingLot(parkingLot);

    }






}
