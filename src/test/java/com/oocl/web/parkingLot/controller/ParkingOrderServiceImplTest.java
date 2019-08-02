package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    public void getForecastTimeForFreeParkingSpacesTest() throws ParseException {

        String dataTime = "2019-07-28 11:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = null;
        startDate = sdf.parse(dataTime);
        parkingOrderService.getForecastTimeForFreeParkingSpaces(startDate);

        System.out.println();

    }
}
