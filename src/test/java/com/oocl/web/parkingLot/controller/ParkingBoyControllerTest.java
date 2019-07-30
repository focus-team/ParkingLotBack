package com.oocl.web.parkingLot.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        parkingBoyRepository.deleteAll();
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot initParkingLot = new ParkingLot("AA",18,10,"boy");
        parkingLots.add(initParkingLot);
        ParkingBoy initParkingBoy = new ParkingBoy("Tom","", "123454778", 18, "male", "busy", "VIP", parkingLots);
        parkingBoyRepository.save(initParkingBoy);
    }

    @After
    public void clear(){
//        parkingBoyRepository.deleteAll();
    }

    @Test
    public void should_return_ok_when_add_a_parkingLOt() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy("Jemmy11","" ,"123454778", 18, "male", "busy", "VIP", parkingLots);

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
        ParkingBoy parkingBoy1 = new ParkingBoy("Mike","", "123454778", 18, "male", "busy", "VIP", parkingLots);
        ParkingBoy parkingBoy2 = new ParkingBoy("yamy", "","123454778", 18, "male", "busy", "VIP", parkingLots);
        ParkingBoy parkingBoy3 = new ParkingBoy("Coko","","123454778", 18, "male", "busy", "VIP", parkingLots);

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

    @Test
    public void should_return_parkingboy_when_find_by_id() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy("Joy", "","123454778", 18, "male", "busy", "VIP", parkingLots);

        //when
        MvcResult mvcResultSaved = this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy))).andReturn();
        JSONObject parkingBoySaved = new JSONObject(mvcResultSaved.getResponse().getContentAsString());
        //then
        MvcResult mvcResult = this.mockMvc.perform(get("/parkingboy/"+ parkingBoySaved.getLong("id")))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertEquals("male",jsonObject.getString("sex"));

    }

    @Test
    public void should_update_parking_lot_when_update_by_id() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy("Joy", "","123454778", 18, "male", "busy", "VIP", parkingLots);
        MvcResult mvcResultSaved = this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy))).andReturn();
        JSONObject parkingBoySaved = new JSONObject(mvcResultSaved.getResponse().getContentAsString());

        //when
        parkingBoy.setAge(89);
        MvcResult mvcResult = this.mockMvc.perform(patch("/parkingboy/" + parkingBoySaved.getLong("id"))
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(parkingBoy))).andReturn();

        //then
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(89,jsonObject.getLong("age"));
    }

    @Test
    public void should_delete_a_parking_boy_when_delete_by_id() throws Exception{

        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy("Joy","", "123454778", 18, "male", "busy", "VIP", parkingLots);
        MvcResult mvcResultSaved = this.mockMvc.perform(post("/parkingboy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy))).andReturn();
        JSONObject parkingBoySaved = new JSONObject(mvcResultSaved.getResponse().getContentAsString());
        //when

        //then
        this.mockMvc.perform(delete("/parkingboy/"+parkingBoySaved.getLong("id")))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void should_return_like_filtered_parking_boy_list_when_given_filter_keyword() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setName("T");
        String content = mapper.writeValueAsString(parkingBoy);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/parkingboy/filter")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name']", is("Tom")));

        parkingBoy.setName(null);
        parkingBoy.setStatus("bu");
        content = mapper.writeValueAsString(parkingBoy);
        requestBuilder = MockMvcRequestBuilders.post("/parkingboy/filter")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name']", is("Tom")));

        parkingBoy.setStatus(null);
        parkingBoy.setTag("v");

        content = mapper.writeValueAsString(parkingBoy);
        requestBuilder = MockMvcRequestBuilders.post("/parkingboy/filter")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name']", is("Tom")));
    }

}
