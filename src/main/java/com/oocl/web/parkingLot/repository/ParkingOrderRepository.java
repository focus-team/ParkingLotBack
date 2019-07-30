package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    @Query(value = "select * from `parking_order` where is_over_date = 0 and user_id = :userId",nativeQuery = true)
    ParkingOrder getParkingOrderByNotIsOverDateBOrderByUserId(@Param("userId")Long userId);


    @Override
    Page<ParkingOrder> findAll(Pageable pageable);


    List<ParkingOrder> findParkingOrdersByIsOverDateAndParkingBoyId(int IsOverDate,Long parkingBoyId);



}
