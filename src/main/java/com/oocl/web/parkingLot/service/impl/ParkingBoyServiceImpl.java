package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.common.ServerResponse;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.service.ParkingBoyService;
import com.oocl.web.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingBoyServiceImpl implements ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;


    @Override
    public ParkingBoy findByNameAndPasswd(String name, String password) {
        return parkingBoyRepository.findByNameAndPasswd(name, password);
    }

    @Override
    public ParkingBoy create(ParkingBoy parkingBoy) {

//        if(parkingBoyRepository.findByName(parkingBoy.getName()).size()>1){
//            throw new GlobalException(1,"The parkingBoy name has exited!");
//        }
        try {
            parkingBoy.setPassword("123456");
            parkingBoy.setTag("ORDINARY");
            ParkingBoy save = parkingBoyRepository.save(parkingBoy);
            return save;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new GlobalException(1,"The parkingBoy name has exited!");
        } finally {
        }

    }

    @Override
    public Page<ParkingBoy> getByPage(int page, int pageSize) {

        Pageable pageable  = PageRequest.of(page - 1,pageSize);
        return parkingBoyRepository.findAll(pageable);
    }

    @Override
    public ParkingBoy getById(Long id) {

        return parkingBoyRepository.findById(id).get();
    }

    @Override
    public ServerResponse update(ParkingBoy parkingBoy) {
        ParkingBoy parkingBoy1 = parkingBoyRepository.findById(parkingBoy.getId()).get();
        if(parkingBoy1 == null){
            return ServerResponse.createByErrorMessage("参数错误，无法找到指定的停车员！");
        }
        parkingBoy1.setTag(parkingBoy.getTag());
        parkingBoyRepository.save(parkingBoy1);
        return ServerResponse.createBySuccess();
    }

    @Override
    public void  delete(Long id) {
//        if(parkingBoyRepository.findById(id).isPresent()){
//            parkingBoyRepository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body();
//        }
        parkingBoyRepository.deleteById(id);
    }

    @Override
    public List<ParkingBoy> getParkingBoyByFilterWord(ParkingBoy parkingBoy) {
        List<ParkingBoy> parkingBoyList = null;
        if(parkingBoy.getName() != null) {
            parkingBoyList = parkingBoyRepository.findByNameLike(parkingBoy.getName());
        }
        if(parkingBoy.getStatus() != null) {
            parkingBoyList = parkingBoyRepository.findByStateLike(parkingBoy.getStatus());
        }
        if(parkingBoy.getPhone() != null) {
            parkingBoyList = parkingBoyRepository.findByPhoneLike(parkingBoy.getPhone());
        }
        if(parkingBoy.getTag() != null) {
            parkingBoyList = parkingBoyRepository.findByTagLike(parkingBoy.getTag());
        }
        return parkingBoyList;
    }

    @Override
    public ParkingBoy resetPassword(String name, String newPassword) {
        ParkingBoy parkingBoy = parkingBoyRepository.findByName(name);
        if(parkingBoy == null){
            return null;
        }
        parkingBoy.setPassword(newPassword);
        return parkingBoyRepository.save(parkingBoy);
    }

    @Override
    public ServerResponse fetchOrderManually(String parkingBoyId, String orderId) {
        ParkingOrder parkingOrder = parkingOrderRepository.findById(Long.valueOf(orderId)).get();
        if(parkingOrder.getParkingBoyId() != 0){
            return ServerResponse.createByErrorMessage("当前订单已被其他停车员抢去，请选择其他订单进行抢单操作！");
        }
        ParkingBoy parkingBoy = parkingBoyRepository.findById(Long.valueOf(parkingBoyId)).get();
        parkingOrder.setParkingBoyId(Long.valueOf(parkingBoyId));
        for(ParkingLot parkingLot : parkingBoy.getParkingLots()){
            if(parkingLot.getRemine() > 0){
                parkingOrder.setParkingLotId(parkingLot.getId());
                parkingOrderRepository.save(parkingOrder);
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByErrorMessage("当前停车员所管理的停车场都处于停车饱满状态，无空位停车场可停！");
    }

    @Override
    public ServerResponse<List<ParkingLot>> fetchNotBelongedParkingLotList(Long parkingBoyId) {
        ParkingBoy parkingBoy = parkingBoyRepository.findById(parkingBoyId).get();
        List<ParkingLot> initialParkingLotList = parkingLotRepository.findAll();
        List<ParkingLot> requiredParkingLotList = new ArrayList<>();
        for(ParkingLot parkingLot: initialParkingLotList){
            if(!parkingBoy.getParkingLots().contains(parkingLot) && parkingBoy.getTag().equals(parkingLot.getTag())){
                requiredParkingLotList.add(parkingLot);
            }
        }
        return ServerResponse.createBySuccess(requiredParkingLotList);
    }

    @Override
    public ServerResponse updateParkingBoysParkingLotList(List<ParkingLot> parkingLots, Long parkingBoyId) {
//        ParkingBoy parkingBoy = parkingBoyRepository.findById(parkingBoyId).get();
//        List<ParkingLot> parkingLotsToDelete = parkingBoy.getParkingLots().stream().filter(parkingLot -> !parkingLots.contains(parkingLot))
//                                                .collect(Collectors.toList());
//        List<ParkingLot> parkingLotsToAdd = parkingLots.stream().filter(parkingLot -> !parkingBoy.getParkingLots().contains(parkingLot))
//                                                .collect(Collectors.toList());
//        if(parkingLotsToDelete != null && parkingLotsToDelete.size() > 0){
//            for(ParkingLot parkingLot: parkingLotsToDelete){
//
//            }
//        }
        ParkingBoy parkingBoy = parkingBoyRepository.findById(parkingBoyId).get();
        parkingBoy.setParkingLots(parkingLots);
        parkingBoyRepository.save(parkingBoy);
        return ServerResponse.createBySuccess();
    }
}
