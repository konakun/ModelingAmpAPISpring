package com.konakun.modeling_amps.power;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@Entity
@Table(name = "power")
@EnableAutoConfiguration
public class Power {
    @Id
    @GeneratedValue
    private Integer pk_power;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int wattage;

    @Column(nullable = false)
    private boolean active;
}
