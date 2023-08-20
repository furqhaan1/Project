package com.furru.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.furru.dto.UserDTO;
import com.furru.entity.User;
import com.furru.exception.WanderLustException;
import com.furru.repository.UserRepository;

import com.furru.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;
	

	@Test
	public void invalidLoginInvalidUser() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword("xyz");

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}

	@Test
	public void invalidLoginNullPassword() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword(null);

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}


	
	@Test
	public void validUserRegister() throws Exception {
		UserDTO u=new UserDTO();
		u.setContactNumber("9876543245");
		u.setUserName("Johny Deep");
		u.setPassword("Scott@123");
		u.setEmailId("johnydeep@furru.com");
		Mockito.when(userRepository.findByContactNumber(u.getContactNumber())).thenReturn(null);
		String username=userServiceImpl.registerUser(u);
		Assertions.assertEquals(u.getUserName(), username);
	}
	
	@Test
	public void invalidUserRegister() throws Exception {
		UserDTO u=new UserDTO();
		u.setContactNumber("9876543210");
		u.setUserName("johny deep");
		u.setPassword("xyz@dc");
		u.setEmailId("johnydeep@gamil.com");
		User u1=new User();
		u1.setContactNumber("9876543210");
		u1.setUserName("johny deep");
		u1.setPassword("xyz@dc");
		u1.setEmailId("johnydeep@gamil.com");
		Mockito.when(userRepository.findByContactNumber(u.getContactNumber())).thenReturn(u1);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class, ()-> userServiceImpl.registerUser(u));
		Assertions.assertEquals("userService.CONTACT_NUMBER_ALREADY_EXISTS", exception.getMessage());
	}
}
		
