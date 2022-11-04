package com.konakun.modeling_amps.wattage;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wattages")
@EnableAutoConfiguration
public class Wattage {
    @Id
    @GeneratedValue
    private Integer pk_wattage;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int wattage;

    @Column(nullable = false)
    private boolean active;
}
