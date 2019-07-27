package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingBoyRepository extends JpaRepository<ParkingBoy,Long> {


}
