package com.konakun.modeling_amps.modeler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "modeler")
public class Modeler {
	@Id
	@GeneratedValue
	private Long pk_modeler;
	
	@Column(nullable = false)
	private Long pk_brand;
	
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
