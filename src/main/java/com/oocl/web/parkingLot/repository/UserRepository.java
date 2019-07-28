package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


    public interface UserRepository extends JpaRepository<User, Long> {

        @Query(nativeQuery = true,value = "select * from User where username=:username")
        User findRightUser(@Param("username")String username, @Param("password") String password);
}
