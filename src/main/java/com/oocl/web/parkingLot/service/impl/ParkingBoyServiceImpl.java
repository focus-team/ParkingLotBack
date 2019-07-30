package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ParkingBoyServiceImpl implements ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Override
    public ParkingBoy create(ParkingBoy parkingBoy) {

//        if(parkingBoyRepository.findByName(parkingBoy.getName()).size()>1){
//            throw new GlobalException(1,"The parkingBoy name has exited!");
//        }
        try {
            ParkingBoy save = parkingBoyRepository.save(parkingBoy);
            return save;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new GlobalException(1,"The parkingBoy name has exited!");
        } finally {
        }

    }

    @Override
    public Page<ParkingBoy> getByPage(int page, int pageSize) {

        Pageable pageable  = PageRequest.of(page - 1,pageSize);
        return parkingBoyRepository.findAll(pageable);
    }

    @Override
    public ParkingBoy getById(Long id) {

        return parkingBoyRepository.findById(id).get();
    }

    @Override
    public ParkingBoy update(Long id, ParkingBoy parkingBoy) {
        ParkingBoy oldParkingBoy = this.getById(id);

        System.out.println(JSON.toJSONString(parkingBoy.getPhone()));
        return parkingBoyRepository.save(parkingBoy);
    }

    @Override
    public void  delete(Long id) {
//        if(parkingBoyRepository.findById(id).isPresent()){
//            parkingBoyRepository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body();
//        }
        parkingBoyRepository.deleteById(id);
    }
}
