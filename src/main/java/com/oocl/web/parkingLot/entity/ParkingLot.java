package com.oocl.web.parkingLot.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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

    @Column
    @ApiModelProperty("停车场类型：1.VIP 2.普通")
    private String tag;

    public ParkingLot(String name, Integer capacity, Integer remine, String tag) {
        this.name = name;
        this.capacity = capacity;
        this.remine = remine;
        this.tag = tag;
    }


}
