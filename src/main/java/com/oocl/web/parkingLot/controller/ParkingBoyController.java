package com.oocl.web.parkingLot.controller;


import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
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

    @PostMapping
    private ResponseEntity add(@RequestBody ParkingBoy parkingBoy){
        ParkingBoy parkingBoySaved = parkingBoyService.create(parkingBoy);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingBoySaved);
    }

    @GetMapping(params = {"page","pageSize"})
    public ResponseEntity getByPage(@RequestParam int page, @RequestParam int pageSize){
        Page<ParkingBoy> parkingBoyPage = parkingBoyService.getByPage(page, pageSize);
        return ResponseEntity.ok().body(parkingBoyPage.getContent());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        ParkingBoy parkingBoy = parkingBoyService.getById(id);
        return ResponseEntity.ok().body(parkingBoy);
    }

}
