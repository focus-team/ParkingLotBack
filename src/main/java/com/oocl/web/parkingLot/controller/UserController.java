package com.oocl.web.parkingLot.controller;

import com.oocl.web.parkingLot.common.ResponseStatus;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins ="*")
public class UserController {

    private static final String CURRENT_USER= "CURRENT_USER";
    @Autowired
    private UserService userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user, HttpSession httpSession) {
        User u =  userServiceImpl.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(u == null){
            return ResponseEntity.status(ResponseStatus.NOT_FOUND.getStatusCode()).body(ResponseStatus.NOT_FOUND.getStatusDesc());
        }
        httpSession.setAttribute(CURRENT_USER, u);
        return ResponseEntity.ok(ResponseStatus.SUCCESS.getStatusDesc());
    }
}