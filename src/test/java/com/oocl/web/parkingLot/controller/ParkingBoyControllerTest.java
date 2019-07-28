package com.oocl.web.parkingLot.controller;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingBoyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Before
    public void init() throws Exception{
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot initParkingLot = new ParkingLot("AA",18,"AA");
        parkingLots.add(initParkingLot);
        ParkingBoy initParkingBoy = new ParkingBoy("Tom", "123454778", 18, "male", "busy", "VIP", parkingLots);
        parkingBoyRepository.save(initParkingBoy);
    }

    @After
    public void clear(){
        parkingBoyRepository.deleteAll();
    }

    @Test
    public void should_return_ok_when_add_a_parkingLOt() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy("Jemmy", "123454778", 18, "male", "busy", "VIP", parkingLots);

        //when
        //then
        MvcResult mvcResult = this.mockMvc.perform(post("/parkingboy").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void should_return_parkinglot_page_when_select_by_page() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy1 = new ParkingBoy("Mike", "123454778", 18, "male", "busy", "VIP", parkingLots);
        ParkingBoy parkingBoy2 = new ParkingBoy("yamy", "123454778", 18, "male", "busy", "VIP", parkingLots);
        ParkingBoy parkingBoy3 = new ParkingBoy("Coko", "123454778", 18, "male", "busy", "VIP", parkingLots);

        //when
        this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy1)));
        this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy2)));
        this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy3)));

        //then
        MvcResult mvcResult = this.mockMvc.perform(get("/parkingboy?page=1&pageSize=2"))
                .andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(2,jsonArray.length());
    }

}
