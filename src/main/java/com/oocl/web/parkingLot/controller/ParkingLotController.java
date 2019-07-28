package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:35
 * @description:
 */

@RestController
@RequestMapping("/parkinglots")
@CrossOrigin(origins = "*")
public class ParkingLotController {


    @Autowired
    private ParkingLotService parkingLotService;


    @PostMapping
    public ParkingLot saveParkingLot(@RequestBody ParkingLot parkingLot){
        return parkingLotService.saveParkingLot(parkingLot);
    }

    @PutMapping
    public ParkingLot updateParkingLot(@RequestBody ParkingLot parkingLot){
        return parkingLotService.saveParkingLot(parkingLot);
    }


    @DeleteMapping("/{id}")
    public void deleteParkingLot(@PathVariable Long id){
        parkingLotService.deleteParkingLotById(id);
    }











}
