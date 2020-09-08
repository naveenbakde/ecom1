package com.dev.demo.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Cart implements Serializable {

	private static final long serialVersionUID = 6644628677199437743L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Mobile> mobileList = new ArrayList<>();
	
	@OneToOne
	@JsonIgnore
	private User user;
	
	private List<Mobile> getCartProducts() {
		return mobileList;
	}
	
	public void addProductToCart(Mobile mobile) {
		Objects.requireNonNull(mobile);		
		getCartProducts().add(mobile);		
	}

	public Cart(Mobile mobile, User user) {
		mobileList.add(mobile);
		this.user = user;
	}
	
}
