package com.dev.demo.ecom.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data 
@Builder(toBuilder = true)
@JsonPropertyOrder({"company", "model", "name", "color", "ram", "memory", "os"})
public class Mobile implements Comparable<Mobile> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String name;
	private String company;
	private String color;
	private String model;
	private String os;
	private Integer ram;
	private Integer memory;
	@Builder.Default
	private double price = 100;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Stock stock;
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Mobile)) 
			return false;
		
		if (this == obj) 
			return true;
		
		Mobile that = (Mobile) obj;
		
		return (this.company.equals(that.company) && this.model.equals(that.model) && this.color.equals(that.color) && this.os.equals(that.os)) ? true : false;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(company, model, color, ram, memory, name);
	}

	@Override
	public int compareTo(Mobile m1) {
		Objects.requireNonNull(m1);
		return (this.company.equals(m1.company) && this.model.equals(m1.model) && 
				this.color.equals(m1.color) && this.ram == m1.ram && this.memory == m1.memory &&
				this.os.equals(m1.os)) ? 1 : 0;
	}

}
