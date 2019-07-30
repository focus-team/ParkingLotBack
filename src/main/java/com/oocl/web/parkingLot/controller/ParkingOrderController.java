package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.dto.OrderDetailDTO;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="ParkingOrderApi",description = "订单操作接口")
@RequestMapping("/orderdetailsdto")
@CrossOrigin(origins = "*")
public class ParkingOrderController {


    @Autowired
    private ParkingOrderService parkingOrderService;


    @ApiOperation(value = "获取所有订单")
    @GetMapping(params = {"page", "size"})
    List<OrderDetailDTO> getOrderDTOList(@RequestParam(name = "page",defaultValue = "0") int pageNum,
                                         @RequestParam(name = "size",defaultValue = "10") int pageSize){

        return parkingOrderService.getOrderDetailDTOs(pageNum,pageSize);

    }




}
