package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.dto.OrderDTO;

import com.oocl.web.parkingLot.dto.OrderDetailsDTO;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="ParkingOrderApi",description = "订单操作接口")
@RequestMapping("/paringorders")
@CrossOrigin(origins = "*")
public class ParkingOrderController {


    @Autowired
    private ParkingOrderService parkingOrderService;


    @ApiOperation(value = "获取所有订单")
    @GetMapping
    List<OrderDetailsDTO> getOrderDTOList(){
        return null;
    }




}
