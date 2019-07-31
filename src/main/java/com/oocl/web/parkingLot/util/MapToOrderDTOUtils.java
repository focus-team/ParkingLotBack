package com.oocl.web.parkingLot.util;

import com.oocl.web.parkingLot.dto.OrderDTO;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;


/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/31
 * @Time:20:48
 * @description:
 */
public class MapToOrderDTOUtils {



    public static OrderDTO getDTOByMap(Map map){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId( ((BigInteger) map.get("id")).longValue());
        orderDTO.setCost((Integer) map.get("cost"));
        orderDTO.setStartTime((Date) map.get("start_time"));
        orderDTO.setEndTime((Date) map.get("end_time"));
        orderDTO.setParkingLotName((String) map.get("parking_lot_name"));
        orderDTO.setOrderNum((String) map.get("order_num"));
        orderDTO.setUserName((String) map.get("user_name"));
        orderDTO.setParkingBoyName((String) map.get("parking_boy_name"));
        orderDTO.setParkingBoyTel((String) map.get("parking_boy_tel"));
        orderDTO.setIsOverDate((Integer) map.get("is_over_date"));

        return orderDTO;

    }



}
