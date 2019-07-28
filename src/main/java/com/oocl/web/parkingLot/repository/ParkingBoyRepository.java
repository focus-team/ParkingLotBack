package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingBoyRepository extends JpaRepository<ParkingBoy,Long> {

        Page<ParkingBoy> findAll(Pageable pageable);
        List<ParkingBoy> findByName(String naeme);
}
