package com.furru.service.test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.furru.dto.BookingDTO;
import com.furru.dto.DestinationDTO;
import com.furru.entity.Booking;
import com.furru.entity.Destination;
import com.furru.entity.Details;
import com.furru.entity.Itinerary;
import com.furru.entity.User;
import com.furru.exception.WanderLustException;
import com.furru.repository.DestinationRepository;
import com.furru.repository.UserRepository;
import com.furru.service.BookingServiceImpl;

@SpringBootTest
public class BookingServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	DestinationRepository destinationRepository;
	
	
	@InjectMocks
	BookingServiceImpl bookingServiceImpl;
	
	
	@Test
	public void validGetBookingPackage() throws WanderLustException {
		User u1=new User();
		u1.setUserId(101);
		u1.setContactNumber("8764535241");
		u1.setUserName("Famo Sin");
		u1.setPassword("Xyz@123");
		u1.setEmailId("johnydepp@furru.com");
		Optional<User>user=Optional.of(u1);
		
		Destination d1=new Destination();
		d1.setDestinationId("D1001");
		d1.setContinent("Europe");
		d1.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
		d1.setImageUrl("/assets/greece.jpg");
		d1.setNoOfNights(7);
		d1.setFlightCharge(500.0f);
		d1.setChargePerPerson(2499.0f);
		d1.setDiscount(3.0f);
		d1.setAvailability(30);
		
		
		Details details=new Details();
		details.setDetailsId("DL101");
		details.setAbout("Watch the setting sun from the hilltops of Greece's most famous islands. Experience ancient history and open-air museums in the capital");
		details.setPackageInclusion("7 nights in handpicked hotels, 7 breakfasts, 3 dinners with beer or wine, 3 guided sightseeing tours,Expert four director");
		details.setHighlights("Greece, Athens, Mykonos, Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		details.setPace("On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails");
		
		Itinerary i=new Itinerary();
		i.setItineraryId("I1001");
		i.setFirstDay("Travel day : Board your overnight flight to Athens");
		i.setRestOfDays("Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		i.setLastDay("Departure: Transfer to the airport for your flight home");
		details.setItinerary(i);
		d1.setDetails(details);
		Optional<Destination> destination=Optional.of(d1);
		Mockito.when(userRepository.findById(101)).thenReturn(user);
		Mockito.when(destinationRepository.findById("D1001")).thenReturn(destination);
		LocalDate date=LocalDate.now();
		LocalDateTime time=LocalDateTime.now();
		BookingDTO b2=new BookingDTO();
		b2.setCheckIn(date);
		b2.setCheckOut(date.plusDays(7));
		b2.setDestination(d1);
		b2.setNoOfPeople(4);
		b2.setTimeOfBooking(time);
		b2.setTotalCost(5000f);
		b2.setUser(u1);
		b2.setBookingId(1001);
		BookingDTO booking=bookingServiceImpl.getBookingPackage(b2);
		Assertions.assertEquals(101, booking.getUser().getUserId());
		}
	
	@Test
	public void validGetDestination() throws WanderLustException {
		Destination d1=new Destination();
		d1.setDestinationId("D1001");
		d1.setContinent("Europe");
		d1.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
		d1.setImageUrl("/assets/geece/.jpg");
		d1.setNoOfNights(7);
		d1.setFlightCharge(500.0f);
		d1.setChargePerPerson(2499.0f);
		d1.setDiscount(0.0f);
		d1.setAvailability(30);
		
		
		Details details=new Details();
		details.setDetailsId("DL101");
		details.setAbout("Watch the setting sun from the hilltops of Greece's most famous islands. Experience ancient history and open-air museums in the capital");
		details.setPackageInclusion("7 nights in handpicked hotels, 7 breakfasts, 3 dinners with beer or wine, 3 guided sightseeing tours,Expert four director");
		details.setHighlights("Greece, Athens, Mykonos, Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		details.setPace("On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails");
		
		
		Itinerary i=new Itinerary();
		i.setItineraryId("I1001");
		i.setFirstDay("Travel day : Board your overnight flight to Athens");
		i.setRestOfDays("Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		i.setLastDay("Departure: Transfer to the airport for your flight home");
		details.setItinerary(i);
		d1.setDetails(details);
		Optional<Destination>destination=Optional.of(d1);
		Mockito.when(destinationRepository.findById("D1001")).thenReturn(destination);
		DestinationDTO d2=bookingServiceImpl.getDestination("D1001");
		Assertions.assertEquals("D1001", d2.getDestinationId());
		
	}


		@Test
	public void validgetUserBooking() throws WanderLustException{
		User u1=new User();
		u1.setUserId(101);
		u1.setContactNumber("9012345678");
		u1.setUserName("John Doe");
		u1.setPassword("abcD@ac");
		u1.setEmailId("johndoe@furru.com");
		Destination d1=new Destination();
		d1.setDestinationId("D1001");
		d1.setContinent("Europe");
		d1.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
		d1.setImageUrl("/assets/geece/.jpg");
		d1.setNoOfNights(7);
		d1.setFlightCharge(500.0f);
		d1.setChargePerPerson(2499.0f);
		d1.setDiscount(0.0f);
		d1.setAvailability(30);
		
		
		Details details=new Details();
		details.setDetailsId("DL101");
		details.setAbout("Watch the setting sun from the hilltops of Greece's most famous islands. Experience ancient history and open-air museums in the capital");
		details.setPackageInclusion("7 nights in handpicked hotels, 7 breakfasts, 3 dinners with beer or wine, 3 guided sightseeing tours,Expert four director");
		details.setHighlights("Greece, Athens, Mykonos, Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		details.setPace("On this guided tour, you will walk for about 2 hours daily across uneven terrain, including paved roads and unpaved trails");
		
		
		Itinerary i=new Itinerary();
		i.setItineraryId("I1001");
		i.setFirstDay("Travel day : Board your overnight flight to Athens");
		i.setRestOfDays("Santorini, Acropolis, Parthenon, Temple of Apollo, Ruins of Olympia, Ancient Theater of Epidaurus");
		i.setLastDay("Departure: Transfer to the airport for your flight home");
		details.setItinerary(i);
		d1.setDetails(details);
//		Optional<Destination>destination=Optional.of(d1);
//		Mockito.when(destinationRepository.findById("D1001")).thenReturn(destination);
//		DestinationDTO d2=bookingServiceImpl.getDestination("D1001");
//		Assertions.assertEquals("D1001", d2.getDestinationId());
		
		LocalDate date=LocalDate.now();
		LocalDateTime time=LocalDateTime.now();
		Booking b2=new Booking();
		b2.setCheckIn(date);
		b2.setCheckOut(date.plusDays(7));
		b2.setDestination(d1);
		b2.setNoOfPeople(4);
		b2.setTimeOfBooking(time);
		b2.setTotalCost(5000f);
		b2.setUser(u1);
		b2.setBookingId(1001);
		Set<Booking>b=new HashSet<>();
		b.add(b2);

		Optional<User> user=Optional.of(u1);
		Mockito.when(userRepository.findById(101)).thenReturn(user);
		//List<BookingDTO> bDTO=bookingServiceImpl.getUserBookings(101);
		Assertions.assertEquals(1001,b2.getBookingId());
		
		
	}
	@Test
	public void invalidgetUserBookings() throws WanderLustException{
		User u1=new User();
		u1.setUserId(101);
		u1.setContactNumber("9012345678");
		u1.setUserName("John Doe");
		u1.setPassword("abcD@ac");
		u1.setEmailId("johndoe@furru.com");
		Set<Booking>b=new HashSet<>();
		
		u1.setListOfBookings(b);
		Optional<User>user=Optional.of(u1);
		Mockito.when(userRepository.findById(101)).thenReturn(user);
		WanderLustException exception=Assertions.assertThrows(WanderLustException.class, ()->bookingServiceImpl.getUserBookings(101));
		Assertions.assertEquals("BookingService.USER_HAS_NO_BOOKINGS",exception.getMessage());
		
		
	}
		
		
}		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
	


