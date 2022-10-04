package com.konakun.modeling_amps.device_type;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "device_type")
public class DeviceType {
    @Id
    @GeneratedValue
    private Long pk_type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;
}
