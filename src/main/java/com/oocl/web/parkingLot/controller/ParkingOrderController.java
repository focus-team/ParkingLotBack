package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.dto.OrderDetailDTO;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    List<OrderDetailDTO> OrderDetailDTOList(@RequestParam(name = "page",defaultValue = "0") int pageNum,
                                         @RequestParam(name = "size",defaultValue = "10") int pageSize){

        return parkingOrderService.getOrderDetailDTOs(pageNum,pageSize);

    }

    @ApiOperation(value = "通过订单ID获取详情")
    @GetMapping("/{orderId}")
    OrderDetailDTO getOrderDetailDTO(@PathVariable Long orderId){

        return parkingOrderService.getOrderDetailDTO(orderId);

    }


    @ApiOperation(value = "通过停车员ID获取相关条件的订单")
    @GetMapping("/{parkingBoyId}/{condition}")
    List<OrderDetailDTO> getOrderDTOsWithCondition(@PathVariable Long parkingBoyId,
                                                   @ApiParam("条件内容：0.未接单 1.已预约 2.已完成 ") @PathVariable Long condition){


        return parkingOrderService.getOrderDetailDTOsWithConditon(parkingBoyId,condition);


    }



}
