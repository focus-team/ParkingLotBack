package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.springframework.stereotype.Service;

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


    ParkingLot saveParkingLot(ParkingLot parkingLot);


}
