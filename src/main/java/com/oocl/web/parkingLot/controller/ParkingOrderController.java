package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "停车订单",value="ParkingOrderApi")
@RequestMapping("/paringorder")
@CrossOrigin(origins = "*")
public class ParkingOrderController {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
}
