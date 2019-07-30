package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.FetchCarService;
import com.oocl.web.parkingLot.service.ParkingChargesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:15:16
 * @description:
 */

@Service
public class FetchCarServiceImpl implements FetchCarService {


    private ParkingOrderRepository parkingOrderRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingBoyRepository parkingBoyRepository;
    private UserRepository userRepository;

    @Autowired
    public FetchCarServiceImpl(ParkingOrderRepository parkingOrderRepository, ParkingLotRepository parkingLotRepository, ParkingBoyRepository parkingBoyRepository, UserRepository userRepository) {
        this.parkingOrderRepository = parkingOrderRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingBoyRepository = parkingBoyRepository;
        this.userRepository = userRepository;
    }



    @Override
    public OrderDTO getOrderDTOByUserID(Long userID){

        //通过用户名获取非过期的订单
        ParkingOrder parkingOrder = parkingOrderRepository.getParkingOrderByNotIsOverDateBOrderByUserId(userID);

        if(parkingOrder == null) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(-1L);
            return orderDTO;
        }

        else {

            OrderDTO result = new OrderDTO(parkingOrder);

            User user = userRepository.getOne(parkingOrder.getUserId());
            ParkingLot parkingLot = parkingLotRepository.getOne(parkingOrder.getParkingLotId());
            ParkingBoy parkingBoy = parkingBoyRepository.getOne(parkingOrder.getParkingBoyId());

            result.setCost(0);
            result.setUserName(user.getUserName());
            result.setParkingBoyName(parkingBoy.getName());
            result.setParkingBoyTel(parkingBoy.getPhone());
            result.setParkingLotName(parkingLot.getName());

            return result;

        }

    }



    /**
     * 通过订单预约取车
     * 1.取车默认是同一个停车员
     * 2.取车时间半小时内
     * 3.计费
     *
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO updateParkingOrder(OrderDTO orderDTO) {

        Long endTime = System.currentTimeMillis();
        orderDTO.setEndTime(new Date(endTime));

        //通过停车场名字找停车场,剩余空位+1
        ParkingLot temp = parkingLotRepository.findParkingLotByName(orderDTO.getParkingLotName());
        temp.setRemine(temp.getRemine() + 1);
        //修改parkinglot的剩余量
        parkingLotRepository.save(temp);


        //保存订单 - 设置为过期
        ParkingOrder parkingOrder = parkingOrderRepository.getOne(orderDTO.getId());
        parkingOrder.setEndTime(new Date(endTime));
        parkingOrder.setIsOverDate(1);


        //通过orderDTO计费用
        ParkingChargesStrategy parkingChargesStrategy = new ConcreteParkingChargesA();
        int coast = parkingChargesStrategy.ParkingCharges(orderDTO.getStartTime(),orderDTO.getEndTime(),temp);
        orderDTO.setCost(coast);

        //保存订单 - 保存费用
        parkingOrder.setCost(coast);
        parkingOrderRepository.save(parkingOrder);

        return orderDTO;

    }




}



