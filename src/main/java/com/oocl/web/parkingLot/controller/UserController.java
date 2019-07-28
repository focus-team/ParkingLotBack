package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.common.ResponseStatus;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String CURRENT_USER= "CURRENT_USER";
    @Autowired
    private UserService userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity login(String username, String password, HttpSession httpSession) {
        User user =  userServiceImpl.findUserByUserNameAndPassword(username, password);
        if(user == null){
            return ResponseEntity.status(ResponseStatus.NOT_FOUND.getStatusCode()).body(ResponseStatus.NOT_FOUND.getStatusDesc());
        }
        httpSession.setAttribute(CURRENT_USER, user);
        return ResponseEntity.ok(ResponseStatus.SUCCESS.getStatusDesc());
    }
}
