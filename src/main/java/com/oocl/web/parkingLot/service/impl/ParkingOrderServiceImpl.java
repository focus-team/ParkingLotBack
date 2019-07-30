package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.common.OrderStatusConst;
import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.dto.OrderDetailDTO;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/30
 * @Time:14:34
 * @description:
 */

@Service
public class ParkingOrderServiceImpl  implements ParkingOrderService {

    private ParkingOrderRepository parkingOrderRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingBoyRepository parkingBoyRepository;
    private UserRepository userRepository;

    @Autowired
    public ParkingOrderServiceImpl(ParkingOrderRepository parkingOrderRepository, ParkingLotRepository parkingLotRepository, ParkingBoyRepository parkingBoyRepository, UserRepository userRepository) {
        this.parkingOrderRepository = parkingOrderRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingBoyRepository = parkingBoyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailDTOs(int pageNum,int pageSize) {

        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        List<ParkingOrder> parkingOrders = parkingOrderRepository.findAll(PageRequest.of(pageNum,pageSize)).getContent();

        for(ParkingOrder parkingOrder:parkingOrders){
            orderDetailDTOS.add(transferParkingOrder(parkingOrder));
        }

        return orderDetailDTOS;

    }

    //将parkingOrder转OrderDetailDTO
    private OrderDetailDTO transferParkingOrder(ParkingOrder parkingOrder){

        OrderDTO orderDTO = new OrderDTO(parkingOrder);

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDTO,parkingOrder.getParkingBoyId());


        String state = orderDetailDTO.getState();


        if(state.equals(OrderStatusConst.FINISHED)){


            updateParkingLotName(orderDetailDTO,parkingOrder.getParkingLotId());

            updateUserName(orderDetailDTO,parkingOrder.getUserId());

            updateParkingBoy(orderDetailDTO,parkingOrder.getParkingBoyId());


        }else if(state.equals(OrderStatusConst.SUBSCRIBED)){

            updateParkingLotName(orderDetailDTO,parkingOrder.getParkingLotId());

            updateUserName(orderDetailDTO,parkingOrder.getUserId());

            updateParkingBoy(orderDetailDTO,parkingOrder.getParkingBoyId());



        }
        else{   // (state.equals(OrderStatusConst.UNHANDLED)

            updateUserName(orderDetailDTO,parkingOrder.getUserId());

        }

        return orderDetailDTO;

    }

    //修改 parkinglot
    private void updateParkingLotName(OrderDetailDTO orderDetailDTO,Long id){
        ParkingLot parkingLot = parkingLotRepository.getOne(id);
        orderDetailDTO.setParkingLotName(parkingLot.getName());
    }

    //修改 parkingboy
    private void updateParkingBoy(OrderDetailDTO orderDetailDTO,Long id){
        ParkingBoy parkingBoy = parkingBoyRepository.getOne(id);
        orderDetailDTO.setParkingBoyName(parkingBoy.getName());
        orderDetailDTO.setParkingBoyTel(parkingBoy.getPhone());
    }

    //修改 user
    private void updateUserName(OrderDetailDTO orderDetailDTO,Long id){
        User user = userRepository.getOne(id);
        orderDetailDTO.setUserName(user.getUserName());
    }

    private List<ParkingOrder> getAllAvailableOrdersByPrakingBoyId(Long parkingBoyId){

        String tag = parkingBoyRepository.findById(parkingBoyId).get().getTag();

        List<User> tagUserList = userRepository.findAll().stream().filter(item -> item.getTag().equals(tag)).collect(Collectors.toList());

        List<ParkingOrder> unbookedParkingOrders = new ArrayList<>();
        for(User user : tagUserList){
            List<ParkingOrder> collect = parkingOrderRepository.findAll().stream().filter(item ->
                    item.getUserId() == user.getId()
                            && item.getIsOverDate() == 0
                            && item.getParkingBoyId() == 0)
                    .collect(Collectors.toList());
            unbookedParkingOrders.addAll(collect);
        }
        return unbookedParkingOrders;
    }



}


