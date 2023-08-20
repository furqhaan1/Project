package com.furru.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.furru.dto.UserDTO;
import com.furru.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("UserAPI")
public class UserAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO user) {

		try {
			UserDTO userFromDB = userService.authenticateUser(user.getContactNumber(), user.getPassword());
			return new ResponseEntity<UserDTO>(userFromDB, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

	@PostMapping(value = "/userRegister")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
		try{
			String status = userService.registerUser(user);
			String successMessage = environment.getProperty("UserAPI.REGISTER_USER_SUCCESS1")+" "+status+environment.getProperty("UserAPI.REGISTER_USER_SUCCESS2");
			return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}

	
	}

}
