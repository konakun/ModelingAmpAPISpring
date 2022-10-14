package com.konakun.modeling_amps.device_type;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@Entity
@Table(name = "device_type")
@EnableAutoConfiguration
public class DeviceType {
    @Id
    @GeneratedValue
    private Integer pk_type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;
}
