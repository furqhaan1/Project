package com.furru.validator;

import java.time.LocalDate;

import com.furru.dto.BookingDTO;

public class BookingValidator {

	public static void validateBooking(BookingDTO book) throws Exception{
		if(!validateNumberOfTravellers(book.getNoOfPeople())) {
			throw new Exception("BookingValidator.INVALID_NUMBER_OF_TRAVELLERS");
		}
		else
		if(!validateTripStartDate(book.getCheckIn())) {
			throw new Exception("BookingValidator.INVALID_TRIP_START_DATE");
		}
	}
	
	public static boolean validateTripStartDate(LocalDate checkIn) {
		LocalDate today=LocalDate.now();
		if(today.isBefore(checkIn)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean validateNumberOfTravellers(Integer noOfPeople) {
		if(noOfPeople>=1&&noOfPeople<=5) {
			return true;
		}
		else {
			return false;
		}
	}
}
