package com.furru.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.furru.dto.BookingDTO;
import com.furru.dto.DestinationDTO;
import com.furru.dto.DetailsDTO;
import com.furru.dto.ItineraryDTO;
import com.furru.entity.Booking;
import com.furru.entity.Destination;
import com.furru.entity.User;
import com.furru.exception.WanderLustException;
import com.furru.repository.BookingRepository;
import com.furru.repository.DestinationRepository;
import com.furru.repository.UserRepository;
@Service(value="bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{
@Autowired 
private BookingRepository bookingRepository;
@Autowired 
private DestinationRepository destinationRepository;
@Autowired 
private UserRepository userRepository;

@Override
public BookingDTO getBookingPackage(BookingDTO bookingDTO) throws WanderLustException{
	Booking b1=new Booking();
	Optional<User> u = userRepository.findById(bookingDTO.getUser().getUserId());
	User user = u.orElseThrow(()->new WanderLustException("BookingService.INVALID_USER_DESTINATION"));
	Optional<Destination> d = destinationRepository.findById(bookingDTO.getDestination().getDestinationId());
	Destination destination = d.orElseThrow(()->new WanderLustException("BookingService.INVALID_USER_DESTINATION"));
	
	LocalDate checkOut=bookingDTO.getCheckIn().plusDays(destination.getNoOfNights());
	b1.setCheckIn(bookingDTO.getCheckIn());
	b1.setCheckOut(checkOut);
	b1.setDestination(destination);
	b1.setNoOfPeople(bookingDTO.getNoOfPeople());
	LocalDateTime timeOfBooking=LocalDateTime.now();
	b1.setTimeOfBooking(timeOfBooking);
	b1.setTotalCost(bookingDTO.getTotalCost());
	b1.setUser(user);
	
	BookingDTO b2=new BookingDTO();
	b2.setCheckIn(b1.getCheckIn());
	b2.setCheckOut(b1.getCheckOut());
	b2.setDestination(b1.getDestination());
	b2.setNoOfPeople(b1.getNoOfPeople());
	b2.setTimeOfBooking(b1.getTimeOfBooking());
	b2.setTotalCost(b1.getTotalCost());
	b2.setUser(b1.getUser());
	b2.setBookingId(b1.getBookingId());
	destination.setAvailability(destination.getAvailability() - bookingDTO.getNoOfPeople());
	return b2;
	
}
public DestinationDTO getDestination(String destinationId) throws WanderLustException{
	
	Optional<Destination> destination=destinationRepository.findById(destinationId);
	Destination d=destination.orElseThrow(()->new WanderLustException("BookingService.INVALID_DESTINATION_ID"));
	
	DestinationDTO d1=new DestinationDTO();
	d1.setDestinationId(d.getDestinationId());
	d1.setContinent(d.getContinent());
	d1.setDestinationName(d.getDestinationName());
	d1.setImageUrl(d.getImageUrl());
	d1.setNoOfNights(d.getNoOfNights());
	d1.setChargePerPerson(d.getChargePerPerson());
	d1.setFlightCharge(d.getFlightCharge());
	d1.setDiscount(d.getDiscount());
	d1.setAvailability(d.getAvailability());
	DetailsDTO details=new DetailsDTO();
	ItineraryDTO itinerary=new ItineraryDTO();
	itinerary.setFirstDay(d.getDetails().getItinerary().getFirstDay());
	itinerary.setItineraryId(d.getDetails().getItinerary().getItineraryId());
	itinerary.setRestOfDays(d.getDetails().getItinerary().getRestOfDays());
	itinerary.setLastDay(d.getDetails().getItinerary().getLastDay());
	details.setDetailsId(d.getDetails().getDetailsId());
	details.setAbout(d.getDetails().getAbout());
	details.setPackageInclusion(d.getDetails().getPackageInclusion());
	details.setHighlights(d.getDetails().getHighlights());
	details.setPace(d.getDetails().getPace());
	details.setItinerary(itinerary);
	d1.setDetails(details);
	return d1;
	
	
	
}
@Override
public List<BookingDTO> getUserBookings(Integer userId) throws WanderLustException{
	
	Optional<User> user = userRepository.findById(userId);
	User user1 =user.get();
	Set<Booking> bookset = user1.getListOfBookings();
	if(bookset.isEmpty()) {
		throw new WanderLustException("BookingService.USER_HAS_NO_BOOKINGS");
	}
	List<BookingDTO> bDTO = new ArrayList<>();
	bDTO.forEach((booking) ->{
		BookingDTO bDTO1 = new BookingDTO();
		bDTO1.setCheckIn(booking.getCheckIn());
		bDTO1.setCheckOut(booking.getCheckOut());
		bDTO1.setDestination(booking.getDestination());
		bDTO1.setNoOfPeople(booking.getNoOfPeople());
		bDTO1.setTimeOfBooking(booking.getTimeOfBooking());
		bDTO1.setTotalCost(booking.getTotalCost());
		bDTO1.setUser(booking.getUser());
		bDTO1.setBookingId(booking.getBookingId());
		bDTO.add(bDTO1);
	});
	return bDTO;
}
}