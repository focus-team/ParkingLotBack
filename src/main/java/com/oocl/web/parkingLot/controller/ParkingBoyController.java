package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingboy")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;

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

}
