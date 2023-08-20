package com.furru.service;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furru.dto.UserDTO;
import com.furru.entity.User;
import com.furru.exception.WanderLustException;

import com.furru.repository.UserRepository;
import com.furru.utility.HashingUtility;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException {

		User optionalUser = userRepository.findByContactNumber(contactNumber);
		if (optionalUser == null) {
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = optionalUser.getPassword();

		if (passwordFromDB != null) {
			try {
				String hashedPassword = HashingUtility.getHashValue(password);
				if (hashedPassword.equals(passwordFromDB)) {
					UserDTO userObject = new UserDTO();
					userObject.setContactNumber(optionalUser.getContactNumber());
					userObject.setEmailId(optionalUser.getEmailId());
					userObject.setUserId(optionalUser.getUserId());
					userObject.setUserName(optionalUser.getUserName());
					return userObject;
				} else
					throw new WanderLustException("UserService.INVALID_CREDENTIALS");
			} catch (NoSuchAlgorithmException e) {
				throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
			}

		} else
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");

	}

	@Override
	public String registerUser(UserDTO user) throws WanderLustException {
		
		User user1=userRepository.findByContactNumber(user.getContactNumber());
		if(user1!=null) {
			throw new WanderLustException("userService.CONTACT_NUMBER_ALREADY_EXISTS");
			
		}
		User u=new User();
		try {
			u.setContactNumber(user.getContactNumber());
			u.setUserName(user.getUserName());
			u.setEmailId(user.getEmailId());
			String hashpassword=HashingUtility.getHashValue(user.getPassword());
			u.setPassword(hashpassword);
			
		}
		catch(Exception e) {
			throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
		}
		u=userRepository.save(u);
		return user.getUserName();

	}

	}


