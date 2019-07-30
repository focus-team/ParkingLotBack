package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.common.TagConst;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.service.ParkingChargesStrategy;
import java.util.Date;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:17:30
 * @description:
 *  具体实现计费A：
 *         1.半小时内免费停车
 *         2.不满一小时按一小时计算，
 *         3.普通每小时8元/vip每小时80元
 */
public class ConcreteParkingChargesA implements ParkingChargesStrategy {

    private static int VIP_PRICE = 80;
    private static int ORDINARY_PRICE = 8;


    @Override
    public int ParkingCharges(Date start, Date end, ParkingLot parkingLot) {

        int hours = getHoursByDate(start,end);

        boolean isVip = isVIPparkingLot(parkingLot.getTag());

        if(isVip){
            return hours * VIP_PRICE;
        }else {
            return hours * ORDINARY_PRICE;
        }

    }

    /**
     * 通过日期差距获取整型小时
     * @param start
     * @param end
     * @return
     */
    private int getHoursByDate(Date start,Date end){

        boolean isWithinHalfHour = isWithinHalfHour(start,end);

        if(isWithinHalfHour){
            return 0;
        }
        else {
            return getHoursByCalculateDateDictance(start,end);
        }

    }

    /**
     * 是否半小时以内
     * @param start
     * @param end
     * @return
     */
    private boolean isWithinHalfHour(Date start,Date end){

        Long halfHourMills = 500L * 60 * 60;

        return end.getTime() - start.getTime() <= halfHourMills;

    }

    /**
     * 通过时间差距计算相差几个小时(向上取整)
     * @param start
     * @param end
     * @return
     */
    private int getHoursByCalculateDateDictance(Date start,Date end){

        Double distanceMills = (end.getTime() - start.getTime()) * 1.000;

        int hours = (int) Math.ceil(distanceMills/1000/3600);

        return hours;

    }

    private boolean isVIPparkingLot(String parkingLotName){
        System.out.println(parkingLotName);
        return parkingLotName.equals(TagConst.VIP);
    }


}
