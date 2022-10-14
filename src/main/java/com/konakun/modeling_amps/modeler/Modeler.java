package com.konakun.modeling_amps.modeler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@Table(name = "modeler")
@EnableAutoConfiguration
public class Modeler {
	@Id
	@GeneratedValue
	private Integer pk_modeler;
	
	@Column(nullable = false)
	private Integer pk_brand;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String series;
	
	@Column(nullable = false)
	private String model;
	
	@Column(nullable = false)
	private Long pk_power;
	
	@Column(nullable = false)
	private Long pk_type;
	
	@Column(nullable = false)
	private boolean active;
}
