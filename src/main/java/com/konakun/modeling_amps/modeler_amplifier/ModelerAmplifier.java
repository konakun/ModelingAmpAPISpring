package com.konakun.modeling_amps.modeler_amplifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Data
@Table(name = "modeler_amplifier_models")
@EnableAutoConfiguration
public class ModelerAmplifier {
	@Id
	@GeneratedValue
	private Integer pk_modeler_amplifier_model;
	
	@Column(nullable = false)
	public String name;
	
	@Column(nullable = false)
	public Long pk_modeler;
	
	@Column(nullable = false)
	public Long pk_pre_amp;
	
	@Column(nullable = false)
	public Long pk_power_amp;
	
	@Column(nullable = false)
	public boolean active;
}
