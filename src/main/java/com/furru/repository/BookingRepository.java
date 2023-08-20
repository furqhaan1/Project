package com.furru.repository;


import org.springframework.data.repository.CrudRepository;
import com.furru.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking,Integer> {

	
}


