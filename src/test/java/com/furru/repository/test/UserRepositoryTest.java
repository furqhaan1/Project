package com.furru.repository.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.furru.entity.User;
import com.furru.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setEmailId("fahad@infosys.com");
		user.setPassword("Fahad@123");
		user.setUserId(1234);
		user.setUserName("Fahad rahman");
		user.setContactNumber("9988776655");
	}

	// invalid login
	@Test
	public void invalidLogin() throws Exception {
		Assertions.assertNull(userRepository.findByContactNumber("1234567890"));
	}
}
