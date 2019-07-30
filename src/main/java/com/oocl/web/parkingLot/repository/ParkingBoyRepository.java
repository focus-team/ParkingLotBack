package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingBoyRepository extends JpaRepository<ParkingBoy,Long> {

        @Query(value = "select * from parking_boy where name = :name and password = :password", nativeQuery = true)
        ParkingBoy findByNameAndPasswd(@Param("name") String name, @Param("password") String password);
        Page<ParkingBoy> findAll(Pageable pageable);
        ParkingBoy findByName(String name);
}
