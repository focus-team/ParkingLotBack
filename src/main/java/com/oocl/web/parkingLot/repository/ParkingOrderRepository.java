package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Query(value = "select p.id,p.order_num,p.start_time,p.end_time,p.cost,p.is_over_date,\n" +
            "pb.`name` parking_boy_name,pb.phone parking_boy_tel,pl.`name` parking_lot_name,u.user_name \n" +
            "from parking_order p,`user` u,`parking_lot` pl,`parking_boy` pb\n" +
            "where p.user_id = u.id and pl.id = p.parking_lot_id and pb.id = p.parking_boy_id limit :pageNum,:pageSize",nativeQuery = true)
    List<Map> findAllByPage(@Param("pageNum")int pageNum,@Param("pageSize") int pageSize);


    @Query(value = "select p.id,p.order_num,p.start_time,p.end_time,p.cost,p.is_over_date,\n" +
            "pb.`name` parking_boy_name,pb.phone parking_boy_tel,pl.`name` parking_lot_name,u.user_name \n" +
            "from parking_order p,`user` u,`parking_lot` pl,`parking_boy` pb\n" +
            "where p.user_id = u.id and pl.id = p.parking_lot_id and pb.id = p.parking_boy_id",nativeQuery = true)
    List<Map> findAllOrderDTOs();




    @Query(value = "select p.id,p.order_num,p.start_time,p.end_time,p.cost,p.is_over_date,\n" +
            "pb.`name` parking_boy_name,pb.phone parking_boy_tel,pl.`name` parking_lot_name,u.user_name \n" +
            "from parking_order p,`user` u,`parking_lot` pl,`parking_boy` pb\n" +
            "where p.id = :orderId and p.user_id = u.id and pl.id = p.parking_lot_id and pb.id = p.parking_boy_id",nativeQuery = true)
    Map findOrderDTOById(@Param("orderId") Long orderId);





    @Query(value = "" +
            "select p.id,p.order_num,p.start_time,p.end_time,p.cost,p.is_over_date,\n" +
            "pb.`name` parking_boy_name,pb.phone parking_boy_tel,pl.`name` parking_lot_name,u.user_name \n" +
            "from parking_order p,`user` u,`parking_lot` pl,`parking_boy` pb\n" +
            "where (p.is_over_date = :IsOverDate and p.parking_boy_id = :parkingBoyId) and p.user_id = u.id and pl.id = p.parking_lot_id and pb.id = p.parking_boy_id",
            nativeQuery = true)
    List<Map> findParkingOrdersByIsOverDateAndParkingBoyId(@Param("IsOverDate") int IsOverDate,@Param("parkingBoyId") Long parkingBoyId);





    @Query(value ="SELECT AVG(TIMESTAMPDIFF(MINUTE,start_time,end_time)) from parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getAvgDurationOfCompletedOrders();

    @Query(value = "SELECT MAX(TIMESTAMPDIFF(MINUTE,start_time,end_time)) MaxDuration FROM parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getMaxDurationOfCompletedOrders();

    @Query(value = "SELECT MIN(TIMESTAMPDIFF(MINUTE,start_time,end_time)) MinDuration FROM parking_order po WHERE po.is_over_date = 1",nativeQuery = true)
    String getMinDurationOfCompletedOrders();

    @Query(value = "\n" +
            "SELECT * FROM parking_order WHERE parking_boy_id = 0 AND user_id IN" +
            " (SELECT id FROM USER WHERE tag IN" +
            " (SELECT tag FROM parking_boy WHERE id = :parkingBoyId))")
    List<ParkingOrder> getUnbookedParkingLotsByParkingBoyId(@Param("parkingBoyId") Long parkingBoyId);






}
