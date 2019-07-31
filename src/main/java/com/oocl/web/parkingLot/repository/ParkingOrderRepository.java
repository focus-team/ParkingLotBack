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

    @Query(value = "select * from `parking_order` where is_over_date = 0 and parking_boy_id = :parkingBoyId",nativeQuery = true)
    List<ParkingOrder> getParkingOrderByNotIsOverDateOrderByParkingBoyId(@Param("parkingBoyId")Long parkingBoyId);

    @Override
    Page<ParkingOrder> findAll(Pageable pageable);


    @Query(value = "Select * from parking_order where is_over_date = :IsOverDate and parking_boy_id = :parkingBoyId",nativeQuery = true)
    List<ParkingOrder> findParkingOrdersByIsOverDateAndParkingBoyId(@Param("IsOverDate") int IsOverDate,@Param("parkingBoyId") Long parkingBoyId);



    @Query(value ="SELECT AVG(TIMESTAMPDIFF(MINUTE,start_time,end_time)) from parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getAvgDurationOfCompletedOrders();



    @Query(value = "SELECT MAX(TIMESTAMPDIFF(MINUTE,start_time,end_time)) MaxDuration FROM parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getMaxDurationOfCompletedOrders();

    @Query(value = "SELECT MIN(TIMESTAMPDIFF(MINUTE,start_time,end_time)) MinDuration FROM parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getMinDurationOfCompletedOrders();




}
