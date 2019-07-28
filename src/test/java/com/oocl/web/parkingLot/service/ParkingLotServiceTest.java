package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
    void saveParkingLot() {

        ParkingLot parkingLot = new ParkingLot("foucusTeam_1",10,2);

        ParkingLot result = parkingLotService.saveParkingLot(parkingLot);

        Assertions.assertEquals(result.getName(),result.getName());

    }



}