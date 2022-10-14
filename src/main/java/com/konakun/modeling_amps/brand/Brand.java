package com.konakun.modeling_amps.brand;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@Data
@EnableAutoConfiguration
public class Brand {
    @Id
    @GeneratedValue
    private Integer pk_brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;
}
