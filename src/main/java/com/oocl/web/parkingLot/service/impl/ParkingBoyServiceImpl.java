package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return parkingBoyRepository.save(parkingBoy);
    }

    @Override
    public Page<ParkingBoy> getByPage(int page, int pageSize) {

        return parkingBoyRepository.findAll(PageRequest.of(page-1,pageSize));
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
    public void delete(Long id) {
        parkingBoyRepository.deleteById(id);
    }
}
