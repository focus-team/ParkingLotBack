package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.service.FetchCarService;
import com.oocl.web.parkingLot.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:19:28
 * @description:
 */

@RestController
@Api(value = "parkingordersApi",description = "预约取车接口")
@RequestMapping("/parkingorders")
@CrossOrigin(origins = "*")
public class FetchCarController { 


    @Autowired
    private FetchCarService fetchCarService;

    @ApiOperation(value = "预约停车接口: 通过用户ID获取订单")
    @GetMapping("/{userID}")
    OrderDTO getOrderDTOByID(@PathVariable String userID){
        return null;
    }

    @ApiOperation(value = "预约停车接口: 更新订单)")
    @PutMapping
    OrderDTO updateOrderDTO(@RequestBody OrderDTO orderDTO){
        return null;
    }


}
