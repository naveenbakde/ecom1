package com.dev.demo.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.domain.User;
import com.dev.demo.ecom.security.UserCheck;
import com.dev.demo.ecom.services.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {
	
	private CartService cartService;
	private HttpSession sesssion;
	
	@UserCheck
	@PostMapping("/mobile/addToCart")
	public ResponseEntity<List<Mobile>> addProductToCart(@RequestBody Mobile mobile) {
		try {
			return ResponseEntity.ok(cartService.addProductToCart(mobile, (User)sesssion.getAttribute("user")));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}
	
	@UserCheck
	@GetMapping("/mobile/userCart")
	@ResponseBody
	public List<Mobile> getUserCart() { 
		return cartService.getCartForUser((User)sesssion.getAttribute("user"));
	}
	
	@UserCheck
	@PostMapping("/mobile/buy")
	public ResponseEntity<String> placeOrder() {
		String result = cartService.placeOrder((User)sesssion.getAttribute("user"));
		return result.contains("SUCCESS") ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result);
	}

}
