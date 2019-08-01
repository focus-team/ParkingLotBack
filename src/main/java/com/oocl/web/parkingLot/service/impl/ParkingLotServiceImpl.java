package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:32
 * @description:
 */

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    @Override
    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        if(parkingLot.getId() == null){
            return parkingLotRepository.save(parkingLot);
        }else {
            ParkingLot parkingLot1 = parkingLotRepository.findById(parkingLot.getId()).get();
            if (parkingLot1 == null) {
                return null;
            }
            parkingLot1.setTag(parkingLot.getTag());
            return parkingLotRepository.save(parkingLot1);
        }
    }

    @Override
    public List<ParkingLot> findParkingLots(){
        List<ParkingLot> parkingLots =  parkingLotRepository.findAll();

        return parkingLots.stream().filter(item -> item.getId() != 0).collect(Collectors.toList());
    }


    @Override
    public ParkingLot updateParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }


    @Override
    public void deleteParkingLotById(Long id) {
        parkingLotRepository.deleteById(id);
    }

    @Override
    public List<ParkingLot> findParkingLotsByPage(int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum - 1,pageSize);

        Page<ParkingLot> parkingLots = parkingLotRepository.findAll(pageable);

        List<ParkingLot> result =  parkingLots.getContent().stream().filter(item -> item.getId() != 0).collect(Collectors.toList());

        return result;

    }

    @Override
    public List<ParkingLot> findParkingLotsByPageWithRemine(int remine, int pageNum, int pageSize) {

        pageNum = (pageNum-1)*pageSize;

        return parkingLotRepository.findAllByPageableWithRemine(remine,pageNum,pageSize);

    }
}
