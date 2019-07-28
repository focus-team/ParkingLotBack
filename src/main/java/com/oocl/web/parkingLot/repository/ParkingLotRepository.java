package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {


    Page<ParkingLot> findAll(Pageable pageable);

}
