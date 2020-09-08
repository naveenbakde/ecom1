package com.dev.demo.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.services.MobileSearchServices;

@RestController
@RequestMapping(value = "/api/search")
public class ProductSearchController {
	
	@Autowired
	private MobileSearchServices mobileServices;
	
	@GetMapping("/mobile/allMobiles")
	public ResponseEntity<List<Mobile>> getAllMobiles() {
		return ResponseEntity.accepted().body(mobileServices.findAll());
	}
	
	@GetMapping("/mobile/mobileByName")
	@ResponseBody
	public List<Mobile> getMobilesByName(@RequestParam("mobileName") String mobileName) {
		return mobileServices.getByName(mobileName);
	}
	
	@GetMapping("/mobile/mobileByMemory")
	@ResponseBody
	public List<Mobile> getMobilesByMemory(@RequestParam("ram") Integer ram, @RequestParam("memory") Integer memory) {
		return mobileServices.getByMemory(ram, memory);
	}

}
