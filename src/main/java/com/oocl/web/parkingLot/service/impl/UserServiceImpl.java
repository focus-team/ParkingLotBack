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
    public User findUserByUserNameAndPassword(String username, String password) {
        return userRepository.findByUserName(username, password);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
