package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
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
import com.oocl.web.parkingLot.util.MapToOrderDTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ParkingOrderServiceImpl implements ParkingOrderService {

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
    public List<OrderDetailDTO> getOrderDetailDTOs(int pageNum, int pageSize) {

        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        List<ParkingOrder> parkingOrders = parkingOrderRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();

        for (ParkingOrder parkingOrder : parkingOrders) {
            orderDetailDTOS.add(transferParkingOrder(parkingOrder));
        }

        return orderDetailDTOS;

    }


    @Override
    public List<OrderDetailDTO> getOrderDetailDTOs(){

        List<OrderDetailDTO> result = new ArrayList<>();

        List<Map> list = parkingOrderRepository.findAllOrderDTOsWithSeveralTable();
        for (Map map:list) {
            OrderDetailDTO orderDetailDTO = transferMapToOrderDetailDTO(map);
            result.add(orderDetailDTO);
        }

        return result;

    }


    @Override
    public OrderDetailDTO getOrderDetailDTO(Long orderId) {

        Map map = parkingOrderRepository.findOrderDTOById(orderId);
        return transferMapToOrderDetailDTO(map);

    }


    @Override
    public List<OrderDetailDTO> getOrderDetailDTOsWithConditon(Long parkingBoyId, Long condition) {

        List<ParkingOrder> parkingOrders = null;
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        if (condition == 0) {
            parkingOrders = getAllAvailableOrdersByPrakingBoyId(parkingBoyId);
        } else if (condition == 1) {
            parkingOrders = parkingOrderRepository.findParkingOrdersByIsOverDateAndParkingBoyId(0, parkingBoyId);
        } else if (condition == 2) {
            parkingOrders = parkingOrderRepository.findParkingOrdersByIsOverDateAndParkingBoyId(1, parkingBoyId);
        }

        for (ParkingOrder parkingOrder : parkingOrders) {
            orderDetailDTOS.add(transferParkingOrder(parkingOrder));
        }

        return orderDetailDTOS;

    }


    /**
     *
     * @param map
     * @return
     */
    private OrderDetailDTO transferMapToOrderDetailDTO(Map map){
        OrderDTO orderDTO = MapToOrderDTOUtils.getDTOByMap(map);
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDTO);
        return orderDetailDTO;
    }



    /**
     * 将parkingOrder转OrderDetailDTO
     *
     * @param parkingOrder
     * @return
     */
    private OrderDetailDTO transferParkingOrder(ParkingOrder parkingOrder) {

        OrderDTO orderDTO = new OrderDTO(parkingOrder);

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDTO);


        String state = orderDetailDTO.getState();


        if (state.equals(OrderStatusConst.FINISHED)) {


            updateParkingLotName(orderDetailDTO, parkingOrder.getParkingLotId());

            updateUserName(orderDetailDTO, parkingOrder.getUserId());

            updateParkingBoy(orderDetailDTO, parkingOrder.getParkingBoyId());


        } else if (state.equals(OrderStatusConst.SUBSCRIBED)) {

            updateParkingLotName(orderDetailDTO, parkingOrder.getParkingLotId());

            updateUserName(orderDetailDTO, parkingOrder.getUserId());

            updateParkingBoy(orderDetailDTO, parkingOrder.getParkingBoyId());


        } else {   // (state.equals(OrderStatusConst.UNHANDLED)

            updateUserName(orderDetailDTO, parkingOrder.getUserId());

        }

        return orderDetailDTO;

    }


    /**
     * 修改parkinglot
     *
     * @param orderDetailDTO
     * @param id
     */
    private void updateParkingLotName(OrderDetailDTO orderDetailDTO, Long id) {
        ParkingLot parkingLot = parkingLotRepository.getOne(id);
        orderDetailDTO.setParkingLotName(parkingLot.getName());
    }


    /**
     * 修改 parkingboy
     *
     * @param orderDetailDTO
     * @param id
     */
    private void updateParkingBoy(OrderDetailDTO orderDetailDTO, Long id) {
        ParkingBoy parkingBoy = parkingBoyRepository.getOne(id);
        orderDetailDTO.setParkingBoyName(parkingBoy.getName());
        orderDetailDTO.setParkingBoyTel(parkingBoy.getPhone());
    }


    /**
     * 修改 user
     *
     * @param orderDetailDTO
     * @param id
     */
    private void updateUserName(OrderDetailDTO orderDetailDTO, Long id) {
        User user = userRepository.getOne(id);
        orderDetailDTO.setUserName(user.getUserName());
    }


    private List<ParkingOrder> getAllAvailableOrdersByPrakingBoyId(Long parkingBoyId) {

        String tag = parkingBoyRepository.findById(parkingBoyId).get().getTag();
        System.out.println(tag);
        List<User> tagUserList = userRepository.findAll();
        System.out.println(JSON.toJSONString(tagUserList));
        List<User> tagUserListTure = new ArrayList<>();
        tagUserList.stream().forEach(item -> {
            System.out.println(item.getTag());
            System.out.println(item.getTag().endsWith(tag));
        });
        tagUserList = tagUserList.stream().filter(item -> item.getTag().endsWith(tag)).collect(Collectors.toList());


        System.out.println(JSON.toJSONString(tagUserList));

        List<ParkingOrder> unbookedParkingOrders = new ArrayList<>();
        for (User user : tagUserList) {
            List<ParkingOrder> collect =
                    parkingOrderRepository.findAll().stream().filter(item ->
                            item.getUserId().equals(user.getId())
                                    && item.getIsOverDate() == 0
                                    && item.getParkingBoyId() == 0)
                            .collect(Collectors.toList());

            unbookedParkingOrders.addAll(collect);
        }
        return unbookedParkingOrders;
    }


    @Override
    public double getForecastTimeForFreeParkingSpaces() {
        String avgDurationOfCompletedOrders = parkingOrderRepository.getAvgDurationOfCompletedOrders();
        String maxDurationOfCompletedOrders = parkingOrderRepository.getMaxDurationOfCompletedOrders();
        String minDurationOfCompletedOrders = parkingOrderRepository.getMinDurationOfCompletedOrders();

        double avgDurationOfCompletedOrdersValue = Double.parseDouble(avgDurationOfCompletedOrders);
        double maxDurationOfCompletedOrdersValue = Double.parseDouble(maxDurationOfCompletedOrders);
        double minDurationOfCompletedOrdersValue = Double.parseDouble(minDurationOfCompletedOrders);

        double forecastTimeForFreeParkingSpaces = Math.abs(maxDurationOfCompletedOrdersValue + minDurationOfCompletedOrdersValue - 2 * avgDurationOfCompletedOrdersValue);
        System.out.println(forecastTimeForFreeParkingSpaces);
        return forecastTimeForFreeParkingSpaces;
    }
}


