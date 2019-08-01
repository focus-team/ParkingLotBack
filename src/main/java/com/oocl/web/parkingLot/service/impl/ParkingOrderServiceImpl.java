package com.oocl.web.parkingLot.service.impl;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.dto.OrderDTO;
import com.oocl.web.parkingLot.dto.OrderDetailDTO;
import com.oocl.web.parkingLot.entity.ParkingOrder;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.exception.GlobalException;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.ParkingOrderService;
import com.oocl.web.parkingLot.util.MapToOrderDTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        pageNum = (pageNum - 1) * pageSize;

        List<OrderDetailDTO> result = new ArrayList<>();

        List<Map> list = parkingOrderRepository.findAllByPage(pageNum,pageSize);
        for (Map map:list) {
            OrderDetailDTO orderDetailDTO = transferMapToOrderDetailDTO(map);
            result.add(orderDetailDTO);
        }

        sortByState(result);

        return result;

    }


    @Override
    public List<OrderDetailDTO> getOrderDetailDTOs(){

        List<OrderDetailDTO> result = new ArrayList<>();

        List<Map> list = parkingOrderRepository.findAllOrderDTOs();
        for (Map map:list) {
            OrderDetailDTO orderDetailDTO = transferMapToOrderDetailDTO(map);
            result.add(orderDetailDTO);
        }

        sortByState(result);

        return result;

    }


    @Override
    public OrderDetailDTO getOrderDetailDTO(Long orderId) {

        Map map = parkingOrderRepository.findOrderDTOById(orderId);
        return transferMapToOrderDetailDTO(map);

    }


    @Override
    public List<OrderDetailDTO> getOrderDetailDTOsWithConditon(Long parkingBoyId, Long condition) {

        List<Map> maps = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();


        if (condition == 0) {
            List<ParkingOrder> parkingOrders = getAllAvailableOrdersByPrakingBoyId(parkingBoyId);
           for(ParkingOrder parkingOrder:parkingOrders){
               Map map = parkingOrderRepository.findOrderDTOById(parkingOrder.getId());
               if(map != null) {
                   maps.add(map);
               }
           }
        }
        
        else if (condition == 1) {
            maps = parkingOrderRepository.findParkingOrdersByIsOverDateAndParkingBoyId(0, parkingBoyId);
        }
        else if (condition == 2) {
            maps = parkingOrderRepository.findParkingOrdersByIsOverDateAndParkingBoyId(1, parkingBoyId);
        }

        for (Map map : maps) {
            orderDetailDTOS.add(transferMapToOrderDetailDTO(map));
        }

        return orderDetailDTOS;

    }

    /**
     * 根据订单状态排序
     * @param list
     */
    private void sortByState(List<OrderDetailDTO> list){

        Collections.sort(list, Comparator.comparingInt(OrderDetailDTO::getStatus_code));

    }

    /**
     * map 转 OrderDetailDTO
     * @param map
     * @return
     */
    private OrderDetailDTO transferMapToOrderDetailDTO(Map map){
        OrderDTO orderDTO = MapToOrderDTOUtils.getDTOByMap(map);
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDTO);
        return orderDetailDTO;
    }



    private List<ParkingOrder> getAllAvailableOrdersByPrakingBoyId(Long parkingBoyId) {

//        String tag = parkingBoyRepository.findById(parkingBoyId).get().getTag();
//        System.out.println(tag);
//        List<User> tagUserList = userRepository.findAll();
//        System.out.println(JSON.toJSONString(tagUserList));
//        List<User> tagUserListTure = new ArrayList<>();
//        tagUserList.stream().forEach(item -> {
//            System.out.println(item.getTag());
//            System.out.println(item.getTag().endsWith(tag));
//        });
//        tagUserList = tagUserList.stream().filter(item -> item.getTag().endsWith(tag)).collect(Collectors.toList());
//
//
//        System.out.println(JSON.toJSONString(tagUserList));
//
//        List<ParkingOrder> unbookedParkingOrders = new ArrayList<>();
//        for (User user : tagUserList) {
//            List<ParkingOrder> collect =
//                    parkingOrderRepository.findAll().stream().filter(item ->
//                            item.getUserId().equals(user.getId())
//                                    && item.getIsOverDate() == 0
//                                    && item.getParkingBoyId() == 0)
//                            .collect(Collectors.toList());
//
//            unbookedParkingOrders.addAll(collect);
//        }
////
        List<ParkingOrder> unbookedParkingOrders = new ArrayList<>();
        unbookedParkingOrders = parkingOrderRepository.getUnbookedParkingLotsByParkingBoyId(parkingBoyId);
        return unbookedParkingOrders;
    }


    @Override
    public double getForecastTimeForFreeParkingSpaces(Date startTime)  {

        String avgDurationOfCompletedOrders = parkingOrderRepository.getAvgDurationOfCompletedOrders();
        String maxDurationOfCompletedOrders = parkingOrderRepository.getMaxDurationOfCompletedOrders();
        String minDurationOfCompletedOrders = parkingOrderRepository.getMinDurationOfCompletedOrders();
        if(avgDurationOfCompletedOrders == null){
            avgDurationOfCompletedOrders = "0";
        }
        if(maxDurationOfCompletedOrders == null){
            maxDurationOfCompletedOrders = "0";
        }
        if(minDurationOfCompletedOrders == null){
            minDurationOfCompletedOrders = "0";
        }

        double avgDurationOfCompletedOrdersValue = Double.parseDouble(avgDurationOfCompletedOrders);
        double maxDurationOfCompletedOrdersValue = Double.parseDouble(maxDurationOfCompletedOrders);
        double minDurationOfCompletedOrdersValue = Double.parseDouble(minDurationOfCompletedOrders);

//        double forecastTimeForFreeParkingSpaces = Math.abs(maxDurationOfCompletedOrdersValue + minDurationOfCompletedOrdersValue - 2 * avgDurationOfCompletedOrdersValue);
        System.out.println(avgDurationOfCompletedOrdersValue);

        if(avgDurationOfCompletedOrdersValue > 30d){
            avgDurationOfCompletedOrdersValue = 30d;
        }
        System.out.println(avgDurationOfCompletedOrdersValue);

        double waitingTime =avgDurationOfCompletedOrdersValue;



            System.out.println(startTime.getTime());
            System.out.println((int)avgDurationOfCompletedOrdersValue);
            int minute = this.bookingTimeForecast(startTime.getTime(), (int) avgDurationOfCompletedOrdersValue);
            System.out.println(minute);
            waitingTime = waitingTime > minute ? minute: waitingTime;


//            Map<String, String> data = new HashMap<String, String>();
//
//            data.put("code", "2");
//            data.put("errMessage","input startTime illegal");
//            throw new GlobalException(6, "input startTime illegal",data);
        return waitingTime;

    }


    /**
     *
     * @param longTypeValueOfStartTime 当前想要预约的客户所发起的预约时间，Date类型预先处理为Long类型 (毫秒计)
     * @param caculatedTime 基于大数据算法估算出来的预测时间 (分钟计)
     * @return 真正适合的预估时间 (分钟计)
     */
    @Override
    public int bookingTimeForecast(Long longTypeValueOfStartTime, Integer caculatedTime) {
        List<ParkingOrder> parkingOrderList = parkingOrderRepository.findUnFinishedOrder();
        if(parkingOrderList == null || parkingOrderList.isEmpty()){
            return 0;
        }
        List<Long> orderStartTimeCollection = new ArrayList<>();
        for(ParkingOrder parkingOrder: parkingOrderList){
            orderStartTimeCollection.add(parkingOrder.getStartTime().getTime());
        }
        long mixStartTime = orderStartTimeCollection.stream().mapToLong(time -> time).min().getAsLong();
        return (int)((mixStartTime + caculatedTime * 60 * 1000 - longTypeValueOfStartTime) / 1000 / 60);
    }
}


