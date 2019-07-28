package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findRightUser(String username, String password);

    User getById(Long id);
}
