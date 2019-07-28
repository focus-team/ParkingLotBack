package com.oocl.web.parkingLot.service;

import com.oocl.web.parkingLot.entity.User;

public interface UserService {

    User findUserByUserNameAndPassword(String username, String password);
}
