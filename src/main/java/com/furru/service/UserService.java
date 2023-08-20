package com.furru.service;

import com.furru.dto.UserDTO;
import com.furru.exception.WanderLustException;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException;

	public String registerUser(UserDTO user) throws WanderLustException;

	
}
