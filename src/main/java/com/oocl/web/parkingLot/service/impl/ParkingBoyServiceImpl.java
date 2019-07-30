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

import java.util.List;
@Service
public class ParkingBoyServiceImpl implements ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;


    @Override
    public ParkingBoy findByNameAndPasswd(String name, String password) {
        return parkingBoyRepository.findByNameAndPasswd(name, password);
    }

    @Override
    public ParkingBoy create(ParkingBoy parkingBoy) {

//        if(parkingBoyRepository.findByName(parkingBoy.getName()).size()>1){
//            throw new GlobalException(1,"The parkingBoy name has exited!");
//        }
        try {
            parkingBoy.setPassword("123456");
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

    @Override
    public List<ParkingBoy> getParkingBoyByFilterWord(ParkingBoy parkingBoy) {
        List<ParkingBoy> parkingBoyList = null;
        if(parkingBoy.getName() != null) {
            parkingBoyList = parkingBoyRepository.findByNameLike(parkingBoy.getName());
        }
        if(parkingBoy.getStatus() != null) {
            parkingBoyList = parkingBoyRepository.findByStateLike(parkingBoy.getStatus());
        }
        if(parkingBoy.getPhone() != null) {
            parkingBoyList = parkingBoyRepository.findByPhoneLike(parkingBoy.getPhone());
        }
        if(parkingBoy.getTag() != null) {
            parkingBoyList = parkingBoyRepository.findByTagLike(parkingBoy.getTag());
        }
        return parkingBoyList;
    }

    @Override
    public ParkingBoy resetPassword(String name, String newPassword) {
        ParkingBoy parkingBoy = parkingBoyRepository.findByName(name);
        if(parkingBoy == null){
            return null;
        }
        parkingBoy.setPassword(newPassword);
        return parkingBoyRepository.save(parkingBoy);
    }
}
