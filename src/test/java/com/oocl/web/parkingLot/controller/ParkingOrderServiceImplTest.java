package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:anderson
 * Date:2019/7/31
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingOrderServiceImplTest {
    @Autowired
    private ParkingOrderService parkingOrderService;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Test
    public void getForecastTimeForFreeParkingSpacesTest(){

        parkingOrderService.getForecastTimeForFreeParkingSpaces();

        System.out.println();

    }
}
