package com.oocl.web.parkingLot.entity;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ParkingBoy {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @NotNull
    @Column
    private String phone;

    @NotNull
    @Column
    @Check(constraints = "age>0")
    private Integer age;

    @NotNull
    @Column
    private String sex;

    @NotNull
    private String status;

    @NotNull
    @Column
    private String tag;

    @NotNull
    @Column
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<ParkingLot> parkingLots;


    public ParkingBoy() {
    }

    public ParkingBoy(@NotNull String name, String password, @NotNull String phone, @NotNull Integer age, @NotNull String sex, @NotNull String status, @NotNull String tag, @NotNull List<ParkingLot> parkingLots) {
        this.name = name;
        this.password = "123456";
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.status = status;
        this.tag = tag;
        this.parkingLots = parkingLots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }




}
