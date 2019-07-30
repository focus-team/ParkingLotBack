package com.oocl.web.parkingLot.service;

import org.springframework.http.ResponseEntity;

/**
 * Copyright@xuqiubing@yeah.net
 * Author:lanhusoft
 * Date:2019/7/30
 * Description:系统自动派单的策略
 */
public interface ParkingCarStrategy {
    ResponseEntity ParkingCarStrategy(Long userId, String startTime) throws Exception;
}
