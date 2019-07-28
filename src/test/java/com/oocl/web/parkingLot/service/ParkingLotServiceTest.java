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

        ParkingLot parkingLot = new ParkingLot("foucusTeam_111",10,2);

        ParkingLot result = parkingLotService.saveParkingLot(parkingLot);

        Assertions.assertEquals(result.getName(),parkingLot.getName());
    }

    @Test
    void findParkingLotsTest(){

        List<ParkingLot> parkingLots = parkingLotService.findParkingLots();

        Assertions.assertEquals(parkingLots.size(),11);

    }

    @Test
    void updateParkingLotTest(){

        ParkingLot parkingLot = new ParkingLot("foucusTeam_121",10,3);

        parkingLot.setId(2L);

        ParkingLot result = parkingLotService.updateParkingLot(parkingLot);

        Assertions.assertEquals(result.getName(),parkingLot.getName());

    }


    @Test
    void deleteParkingLotByIdTest(){

        Long id = 1L;

        parkingLotService.deleteParkingLotById(id);

        List<ParkingLot> temp = parkingLotService.findParkingLots();

        Assertions.assertEquals(temp.size(),0);

    }


    @Test
    void findParkingLotsByPageTest(){

        int pageNum = 1;
        int pageSize = 20;

        List<ParkingLot> parkingLots = parkingLotService.findParkingLotsByPage(pageNum,pageSize);

        Assertions.assertEquals(parkingLots.size(),11);

    }
























}