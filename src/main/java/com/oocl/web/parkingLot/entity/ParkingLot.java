package com.oocl.web.parkingLot.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Check;
import javax.persistence.*;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @ApiModelProperty("停车场名字")
    private String name;

    @Check(constraints = "capacity>=0")
    @Column
    @ApiModelProperty("停车场容量")
    private Integer capacity;

    @Column
    @ApiModelProperty("停车场剩余量")
    private Integer remine ;

    public ParkingLot() {

    }

    public ParkingLot(String name, Integer capacity, Integer remine) {
        this.name = name;
        this.capacity = capacity;
        this.remine = remine;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRemine() {
        return remine;
    }

    public void setRemine(Integer remine) {
        this.remine = remine;
    }
}
