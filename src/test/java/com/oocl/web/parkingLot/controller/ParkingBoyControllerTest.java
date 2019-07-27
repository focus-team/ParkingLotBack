package com.oocl.web.parkingLot.controller;

import com.alibaba.fastjson.JSON;
import com.oocl.web.parkingLot.entity.ParkingBoy;
import com.oocl.web.parkingLot.entity.ParkingLot;
import com.oocl.web.parkingLot.repository.ParkingBoyRepository;
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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public void init(){
        parkingBoyRepository.deleteAll();
    }

    @Test
    public void should_return_ok_when_add_a_parkingLOt() throws Exception{
        ParkingBoy parkingBoy = new ParkingBoy("Tom", "123454778", 18, "male", "busy", "VIP", new List<ParkingLot>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<ParkingLot> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(ParkingLot parkingLot) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends ParkingLot> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends ParkingLot> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public ParkingLot get(int index) {
                return null;
            }

            @Override
            public ParkingLot set(int index, ParkingLot element) {
                return null;
            }

            @Override
            public void add(int index, ParkingLot element) {

            }

            @Override
            public ParkingLot remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<ParkingLot> listIterator() {
                return null;
            }

            @Override
            public ListIterator<ParkingLot> listIterator(int index) {
                return null;
            }

            @Override
            public List<ParkingLot> subList(int fromIndex, int toIndex) {
                return null;
            }
        });

        MvcResult mvcResult = this.mockMvc.perform(post("/parkingboy").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(parkingBoy)))
                .andExpect(status().isCreated()).andReturn();
    }

}
