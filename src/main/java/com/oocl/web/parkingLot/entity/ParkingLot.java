package com.oocl.web.parkingLot.entity;

import org.hibernate.annotations.Check;
import javax.persistence.*;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Check(constraints = "capacity>=0")
    @Column
    private Integer capacity;

    @Column
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
