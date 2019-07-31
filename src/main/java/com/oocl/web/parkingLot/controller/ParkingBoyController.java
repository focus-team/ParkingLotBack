package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.common.ResponseStatus;
import com.oocl.web.parkingLot.common.ServerResponse;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import com.oocl.web.parkingLot.common.IdentifyVerifycation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/parkingboy")
@Api(value = "ParkingBoyApi",description = "停车员相关接口")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;


    @ApiOperation(value = "停车员相关接口: 停车员登录")
    @PostMapping(value =  "/login", produces = {"application/json"})
    public ServerResponse<ParkingBoy> login(@ApiParam("停车员对象") @RequestBody ParkingBoy parkingBoy){
        if(parkingBoy.getName() == null || parkingBoy.getPassword() == null){
            return ServerResponse.createByErrorMessage("请检查必填项是否都有填写！");
        }
        ParkingBoy parkingBoy1 = parkingBoyService.findByNameAndPasswd(parkingBoy.getName(), parkingBoy.getPassword());
        if(parkingBoy1 == null){
            return ServerResponse.createByErrorMessage("登录失败，请检查账号跟密码是否正确！");
        }
        return ServerResponse.createBySuccess(IdentifyVerifycation.storeUser(parkingBoy1), parkingBoy1);
    }

    @ApiOperation(value = "停车员相关接口: 停车员修改密码")
    @PutMapping(value = "/reset", produces = {"application/json"})
    public ServerResponse resetPassword(@ApiParam("停车员对象")@RequestBody ParkingBoy parkingBoy){
        if(parkingBoy.getName() == null || parkingBoy.getPassword() == null){
            return ServerResponse.createByErrorMessage("请检查必填项是否都有填写！");
        }
        if(parkingBoyService.resetPassword(parkingBoy.getName(), parkingBoy.getPassword()) == null){
            return ServerResponse.createByErrorMessage("非法的请求数据,请重试！");
        }
        return ServerResponse.createBySuccess();
    }


    @PostMapping(produces = {"application/json"})
    private ResponseEntity add(@RequestBody ParkingBoy parkingBoy){
        ParkingBoy parkingBoySaved = parkingBoyService.create(parkingBoy);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingBoySaved);
    }

    @GetMapping(produces = {"application/json"} ,params = {"page","pageSize"})
    public ResponseEntity getByPage(@RequestParam int page, @RequestParam int pageSize){
        Page<ParkingBoy> parkingBoyPage = parkingBoyService.getByPage(page, pageSize);
        return ResponseEntity.ok().body(parkingBoyPage);
    }

    @GetMapping(produces = {"application/json"} ,path="/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        ParkingBoy parkingBoy = parkingBoyService.getById(id);
        return ResponseEntity.ok().body(parkingBoy);
    }

    @ApiOperation(value = "停车员相关接口: 修改停车员对应的Tag")
    @PatchMapping(produces = {"application/json"})
    public ServerResponse update(@ApiParam("停车员对象")@RequestBody ParkingBoy parkingBoy){
       return parkingBoyService.update(parkingBoy);
    }

    @DeleteMapping(produces = {"application/json"} ,path="/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        parkingBoyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(produces = {"application/json"}, path="/filter")
    public ResponseEntity getAllFilterByCondition(@RequestBody ParkingBoy parkingBoy) {
        List<ParkingBoy> parkingBoyList = parkingBoyService.getParkingBoyByFilterWord(parkingBoy);
        return ResponseEntity.ok().body(parkingBoyList);
    }

    @ApiOperation(value = "停车员相关接口: 停车员手动抢单操作")
    @GetMapping(params = {"parkingBoyId", "orderId"})
    public ServerResponse fetchOrderManually(@ApiParam("停车员Id")@RequestParam("parkingBoyId") String parkingBoyId,
                                             @ApiParam("订单Id")@RequestParam("orderId") String orderId){
        return parkingBoyService.fetchOrderManually(parkingBoyId, orderId);
    }

    @ApiOperation(value = "停车员相关接口: 获取不属于改名停车员的但等级匹配的停车场信息列表")
    @GetMapping(produces = {"application/json"}, params = {"parkingBoyId"})
    public ServerResponse<List<ParkingLot>> fetchNotBelongedParkingLotList(@ApiParam("停车员Id")@RequestParam("parkingBoyId") Long parkingBoyId){
        return parkingBoyService.fetchNotBelongedParkingLotList(parkingBoyId);
    }

    @ApiOperation(value = "停车员相关接口: 更新停车员所管理的停车场列表")
    @PutMapping(produces = {"application/json"}, value = "/updateParkingLotList/{parkingBoyId}")
    public ServerResponse updateParkingBoysParkingLotList(@ApiParam("当前页面存储的指定给改名停车员的停车场列表")@RequestBody List<ParkingLot> parkingLots, @ApiParam("停车员Id")@PathVariable("parkingBoyId") Long parkingBoyId){
        return parkingBoyService.updateParkingBoysParkingLotList(parkingLots, parkingBoyId);
    }

    @ApiOperation(value = "停车员相关接口: 停车员退出登录")
    @PostMapping("/logout")
    public ServerResponse logout(HttpServletRequest httpServletRequest){
        if(IdentifyVerifycation.fetchUser(httpServletRequest) != null){
            IdentifyVerifycation.logoutUser(httpServletRequest);
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("请求非法，请重新登录！");
    }

}
