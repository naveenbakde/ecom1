package com.dev.demo.ecom.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.repository.MobileRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MobileSearchServices {

	private MobileRepository repository;

	public List<Mobile> findAll() {
		log.info("Find all product called...");
		return Optional.ofNullable(repository.findAll()).orElse(Collections.emptyList());
	}

	public List<Mobile> getByCompany(String company) {
		log.info("Find by company colled, Company = {}", company);
		return Optional.of(repository.findByCompany(company)).orElse(Collections.emptyList());
	}

	public List<Mobile> getByName(String name) {
		log.info("Find by name called, name = {}", name);
		return Optional.of(repository.findByName(name)).orElse(Collections.emptyList());
	}

	public List<Mobile> getByMemory(Integer ram, Integer memory) {
		log.info("Find by memory called called, RAM = {}, Memory = {}",ram, memory);
		return Optional.of(repository.findByRamAndMemory(ram, memory)).orElse(Collections.emptyList());
	}

}
