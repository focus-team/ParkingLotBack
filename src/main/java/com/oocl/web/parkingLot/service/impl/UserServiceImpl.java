package com.oocl.web.parkingLot.service.impl;

import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.repository.UserRepository;
import com.oocl.web.parkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<User> userList = userRepository.findAll();
        for(User user1: userList){
            if(user1.getUserName().equals(user.getUserName())){
                return null;
            }
        }
        return userRepository.save(user);
    }
}
