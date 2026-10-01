package com.codingshuttle.SecurityApp.SecurityApplication;

import com.codingshuttle.SecurityApp.SecurityApplication.entity.User;
import com.codingshuttle.SecurityApp.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;


	@Test
	void contextLoads() {
		User user = new User(6L,"harry@455gmail.com","1234","hari");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromTokens(token);

		System.out.println(id);
	}




}
