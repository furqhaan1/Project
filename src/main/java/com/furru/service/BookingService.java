package com.furru.service;



import java.util.List;

import com.furru.dto.BookingDTO;
import com.furru.dto.DestinationDTO;

import com.furru.exception.WanderLustException;

public interface BookingService {
	public BookingDTO getBookingPackage(BookingDTO bookingDTO) throws WanderLustException;
	public DestinationDTO getDestination(String destinationId) throws WanderLustException;
	public List<BookingDTO> getUserBookings(Integer userId) throws WanderLustException;
}



