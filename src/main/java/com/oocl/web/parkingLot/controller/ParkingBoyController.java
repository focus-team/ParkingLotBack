package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.common.ResponseStatus;
import com.oocl.web.parkingLot.common.ServerResponse;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import com.oocl.web.parkingLot.common.IdentifyVerifycation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/parkingboy")
@CrossOrigin(allowCredentials = "true")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;


    @PostMapping(value =  "/login", produces = {"application/json"})
    public ServerResponse<ParkingBoy> login(@RequestBody ParkingBoy parkingBoy){
        if(parkingBoy.getName() == null || parkingBoy.getPassword() == null){
            return ServerResponse.createByErrorMessage("请检查必填项是否都有填写！");
        }
        ParkingBoy parkingBoy1 = parkingBoyService.findByNameAndPasswd(parkingBoy.getName(), parkingBoy.getPassword());
        if(parkingBoy1 == null){
            return ServerResponse.createByErrorMessage("登录失败，请检查账号跟密码是否正确！");
        }
        return ServerResponse.createBySuccess(IdentifyVerifycation.storeUser(parkingBoy1), parkingBoy1);
    }

    @PutMapping(value = "/reset", produces = {"application/json"})
    public ServerResponse resetPassword(@RequestBody ParkingBoy parkingBoy){
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
        return ResponseEntity.ok().body(parkingBoyPage.getContent());
    }

    @GetMapping(produces = {"application/json"} ,path="/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        ParkingBoy parkingBoy = parkingBoyService.getById(id);
        return ResponseEntity.ok().body(parkingBoy);
    }

    @PatchMapping(produces = {"application/json"} ,path="/{id}")
    public ResponseEntity updateById(@PathVariable Long id,@RequestBody ParkingBoy parkingBoy){
        ParkingBoy update = parkingBoyService.update(id, parkingBoy);
        return ResponseEntity.ok().body(update);
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


}
