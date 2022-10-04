package com.konakun.modeling_amps.brand;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "brands")
@Data
public class Brand {
    @Id
    @GeneratedValue
    private Long pk_brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;
}
