package com.dev.demo.ecom.security;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dev.demo.ecom.domain.User;
import com.dev.demo.ecom.exception.InvalidUserException;
import com.dev.demo.ecom.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class UserCheckAspect {
	
	@Autowired
	private UserService userService;
	
	
	@Before("@annotation(UserCheck)")
	private void userCheck() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String userId = request.getHeader("user");
		log.info("Received Request from user# {}", userId);
		if (userId == null) {
			throw new InvalidUserException("Invalid User: "+ userId);
		}
		
		User user = userService.findUserById(Long.valueOf(userId));
		if (user == null) {
			throw new InvalidUserException("Invalid User");
		}
		request.getSession().setAttribute("user", user);
	}

}
