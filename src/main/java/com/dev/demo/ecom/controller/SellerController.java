package com.dev.demo.ecom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.services.MobileCreateService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/seller")
@AllArgsConstructor
public class SellerController {
	
	private MobileCreateService mobileCreateService;
	
	@PostMapping("/moblie/addNew")
	@ResponseBody
	public Mobile addNewMobile(@RequestBody Mobile mobile) {
		return mobileCreateService.addNewMobile(mobile);		
	}

}
