package com.dev.demo.ecom.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Seller implements Serializable {

	private static final long serialVersionUID = -484623635870506243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellerId;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
