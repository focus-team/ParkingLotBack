package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.ParkingBoy;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ParkingBoyService {

    ParkingBoy create(ParkingBoy parkingBoy);

    Page<ParkingBoy> getByPage(int page, int pageSize);

    ParkingBoy getById(Long id);

    ParkingBoy update(Long id, ParkingBoy parkingBoy);

    void delete(Long id);

    List<ParkingBoy> getParkingBoyByFilterWord(ParkingBoy parkingBoy);

}
