package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:43
 * @description:
 */
@SpringBootTest
class ParkingLotServiceTest {


    @Autowired
    private ParkingLotService parkingLotService;

    @Test
    void saveParkingLotTest() {

        ParkingLot parkingLot = new ParkingLot("foucusTeam_1",10,2);

        ParkingLot result = parkingLotService.saveParkingLot(parkingLot);

        Assertions.assertEquals(result.getName(),result.getName());
    }

    @Test
    void findParkingLotsTest(){

        List<ParkingLot> parkingLots = parkingLotService.findParkingLots();

        Assertions.assertEquals(parkingLots.size(),1);

    }

    @Test
    void updateParkingLotTest(){


        ParkingLot parkingLot = new ParkingLot("foucusTeam_0",10,2);

        parkingLot.setId(1L);

        ParkingLot result = parkingLotService.saveParkingLot(parkingLot);

        Assertions.assertEquals(result.getName(),parkingLot.getName());


    }













}