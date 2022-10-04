package com.konakun.modeling_amps.power;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "power")
public class Power {
    @Id
    @GeneratedValue
    private Long pk_power;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int wattage;

    @Column(nullable = false)
    private boolean active;
}
