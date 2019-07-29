package com.oocl.web.parkingLot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.parkingLot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_return_success_when_login_given_right_username_and_password() throws Exception {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");

        String content = mapper.writeValueAsString(user);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void should_return_success_when_register_with_correct_user_info() throws Exception{
        User user = new User();
        user.setUserName("cus2");
        user.setPassword("cus3");
        user.setCarNo("粤MS1221");
        user.setPhoneNo("13100000000");

        String requestBody = mapper.writeValueAsString(user);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/register")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }




}