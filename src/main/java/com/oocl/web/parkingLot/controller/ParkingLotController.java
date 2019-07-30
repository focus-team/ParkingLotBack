package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:35
 * @description:
 */

@RestController
@Api(value = "ParkinglotsApi",description = "停车管理接口")
@RequestMapping("/parkinglots")
@CrossOrigin(origins = "*")
public class ParkingLotController {


    @Autowired
    private ParkingLotService parkingLotService;

    @ApiOperation(value = "停车管理接口: 创建新的停车场(新增)")
    @PostMapping
    public ParkingLot saveParkingLot(
            @ApiParam("停车场对象")@RequestBody ParkingLot parkingLot){
        parkingLot.setRemine(parkingLot.getCapacity());
        return parkingLotService.saveParkingLot(parkingLot);
    }

    @ApiOperation(value = "停车管理接口: 更新停车场相关信息")
    @PutMapping
    public ParkingLot updateParkingLot(@ApiParam("停车场对象")@RequestBody ParkingLot parkingLot){
        return parkingLotService.saveParkingLot(parkingLot);
    }


    @DeleteMapping("/{id}")
    public void deleteParkingLot(@PathVariable Long id){
        parkingLotService.deleteParkingLotById(id);
    }


    @GetMapping(params = {"pageNum","pageSize"})
    public List<ParkingLot> findParkingLotsByPage(@RequestParam int pageNum, @RequestParam int pageSize){
        return parkingLotService.findParkingLotsByPage(pageNum,pageSize);
    }


    @GetMapping(params = {"remine","pageNum","pageSize"})
    public List<ParkingLot> findParkingLotsByPageAndRemine(@RequestParam int remine, @RequestParam int pageNum, @RequestParam int pageSize){

        return  parkingLotService.findParkingLotsByPageWithRemine(remine,pageNum,pageSize);

    }


}
