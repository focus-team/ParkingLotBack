package com.oocl.web.parkingLot.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.parkingLot.common.StatusConst;
import com.oocl.web.parkingLot.common.TagConst;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.entity.User;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import com.oocl.web.parkingLot.repository.ParkingLotRepository;
import com.oocl.web.parkingLot.repository.ParkingOrderRepository;
import com.oocl.web.parkingLot.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Before
    public void init() throws Exception{
        parkingOrderRepository.deleteAll();
        parkingBoyRepository.deleteAll();
        userRepository.deleteAll();
        parkingLotRepository.deleteAll();

    }
    @Test
    public void should_return_success_when_login_given_right_username_and_password() throws Exception {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");

        String content = mapper.writeValueAsString(user);

        final RequestBuilder requestBuilder = post("/user/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void should_return_order_info_when_park_car_and_valuable_boy()throws Exception{

        ParkingLot parkingLotFull = new ParkingLot("AA",10,0,TagConst.VIP);
        ParkingLot parkingLotEmpity = new ParkingLot("BB",10,3,TagConst.VIP);
        //given
        List<ParkingLot> parkingLotsVip = new ArrayList<>();
        List<ParkingLot> parkingLotsOrdinary = new ArrayList<>();
        parkingLotsVip.add(parkingLotFull);
        parkingLotsVip.add(parkingLotEmpity);

        ParkingBoy parkingBoyOrdinary = new ParkingBoy("Mike", "123454778", 18, "male", StatusConst.FREE, TagConst.ORDINARY, parkingLotsOrdinary);
        ParkingBoy parkingBoyVipBusy = new ParkingBoy("yamy", "123454778", 18, "male", StatusConst.BUSY, TagConst.VIP, parkingLotsVip);
        ParkingBoy parkingBoyVipFree = new ParkingBoy("Coko", "123454778", 18, "male", StatusConst.FREE, TagConst.VIP, parkingLotsVip);
        //when
        this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoyOrdinary)));
        this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoyVipBusy)));
        MvcResult mvcResultBoy = this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoyVipFree)))
                .andExpect(status().isCreated()).andReturn();
//        JSONObject jsonObject = new JSONObject(mvcResultBoy.getResponse().getContentAsString());

        //then
        MvcResult mvcResult = this.mockMvc.perform(get("/user/park?userId=1"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
//        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
//        JSONArray jsonObject = new JSONArray(mvcResult.getResponse().getContentAsString());
//        Assertions.assertEquals(parkingBoyVipFree.getName(),jsonObject.getString("parkingBoyName"));

    }
}