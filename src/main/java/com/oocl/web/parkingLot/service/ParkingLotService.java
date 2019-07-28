package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/28
 * @Time:12:31
 * @description:
 */

@Service
public interface ParkingLotService {

    /**
     * 添加ParkingLot
     * @param parkingLot
     * @return
     */
    ParkingLot saveParkingLot(ParkingLot parkingLot);


    /**
     * 获取所有的ParkingLot
     * @return
     */
    List<ParkingLot> findParkingLots();


    /**
     * 修改ParkingLot
     * @param parkingLot
     * @return
     */
    ParkingLot updateParkingLot(ParkingLot parkingLot);


    /**
     * 根据id删除ParkingLot
     * @param id
     */
    void deleteParkingLotById(Long id);


}
