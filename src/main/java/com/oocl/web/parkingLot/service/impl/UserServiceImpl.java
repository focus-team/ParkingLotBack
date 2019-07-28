package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User findRightUser(String username, String password) {
        return userRepository.findRightUser(username, password);
    }

    @Override
    public User getById(Long id) {
        return null;
    }
}
