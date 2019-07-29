package com.oocl.web.parkingLot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.parkingLot.common.IdentifyVerifycation;
import com.oocl.web.parkingLot.common.ResponseStatus;
import com.oocl.web.parkingLot.common.ServerResponse;
import com.oocl.web.parkingLot.common.TagConst;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins ="*")
@Api(value = "UserApi",description = "用户相关接口")
public class UserController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userServiceImpl;


    @ApiOperation(value = "用户相关接口: 用户登录")
    @PostMapping("/login")
    public ServerResponse<User> login(@ApiParam("用户对象") @RequestBody User user) {
        User u =  userServiceImpl.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(u == null){
            return ServerResponse.createByErrorCodeAndMessage(ResponseStatus.NOT_FOUND.getStatusCode(), ResponseStatus.NOT_FOUND.getStatusDesc());
        }
        return ServerResponse.createBySuccess(IdentifyVerifycation.storeUser(u), u);
    }



}
