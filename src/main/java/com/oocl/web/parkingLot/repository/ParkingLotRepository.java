package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {



    @Override
    Page<ParkingLot> findAll(Pageable pageable);


    @Query(value = "SELECT * from parking_lot where remine > :remine limit :pageNum,:pageSize",nativeQuery = true)
    List<ParkingLot> findAllByPageableWithRemine(@Param("remine") int remine, @Param("pageNum")int pageNum, @Param("pageSize")int pageSize);



    ParkingLot findParkingLotByName(String name);

    @Query(value = "SELECT sum(remine) from parking_lot where tag = :tag",nativeQuery = true)
    Integer findParkingLotsRemineByTag(@Param("tag") String tag);




}
