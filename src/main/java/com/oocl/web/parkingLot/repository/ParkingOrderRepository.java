package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:15:39
 * @description:
 */
@Repository
public interface ParkingOrderRepository extends JpaRepository<ParkingOrder,Long> {






}
