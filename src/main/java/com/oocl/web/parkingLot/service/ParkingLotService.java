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


    /**
     * 根据页码数和页码长度获取list
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ParkingLot> findParkingLotsByPage(int pageNum,int pageSize);


    /**
     * 根据页码数和页码长度以remine条件分页获取list
     * @param remine
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ParkingLot> findParkingLotsByPageWithRemine(int remine,int pageNum,int pageSize);





}
