package com.furru.repository;

import org.springframework.data.repository.CrudRepository;

import com.furru.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByContactNumber(String contactNumber);

}
