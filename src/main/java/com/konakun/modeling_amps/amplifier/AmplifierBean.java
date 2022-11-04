package com.konakun.modeling_amps.amplifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AmplifierBean {
    private Integer pk_amplifier;
    private String name;
    private String series;
    private String model;
    private String power;
    private String brand;
    private Boolean active;
}
