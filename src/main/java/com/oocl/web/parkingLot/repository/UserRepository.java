package com.oocl.web.parkingLot.repository;

import com.oocl.web.parkingLot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select id, user_name, password from user where user_name = :user_name and password = :password", nativeQuery = true)
    User findByUserName(@Param("user_name") String user_name, @Param("password")String password);
}
