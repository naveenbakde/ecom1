package com.dev.demo.ecom.services;

import org.springframework.stereotype.Service;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.repository.MobileRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MobileCreateService {
	
	private MobileRepository repository;

	public Mobile addNewMobile(Mobile mobile) {
		log.info("Adding new Mobile : {}", mobile);
		return repository.save(mobile);
	}
	
	

}
