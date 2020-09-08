package com.dev.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.demo.ecom.domain.Cart;
import com.dev.demo.ecom.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByUser(User user);

}
