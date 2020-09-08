package com.dev.demo.ecom.services;

import org.springframework.stereotype.Service;

import com.dev.demo.ecom.domain.User;
import com.dev.demo.ecom.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository repository;
	
	public User findUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
