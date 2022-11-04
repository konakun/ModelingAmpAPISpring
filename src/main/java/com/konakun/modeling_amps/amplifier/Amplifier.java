package com.konakun.modeling_amps.amplifier;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "amplifiers")
@EnableAutoConfiguration
public class Amplifier {
	@Id
    @GeneratedValue
    private Integer pk_amplifier;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String series;
    
    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private Long pk_power;
    
    @Column(nullable = false)
    private boolean active;
}
