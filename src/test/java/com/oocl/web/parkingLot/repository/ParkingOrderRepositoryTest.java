package com.oocl.web.parkingLot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;


/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/31
 * @Time:15:35
 * @description:
 */

@SpringBootTest
class ParkingOrderRepositoryTest {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    void findAllOrderDTOsWithSeveralTable_test() throws SQLException {




    }


}