package com.furru.service.test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.furru.dto.DestinationDTO;
import com.furru.entity.Destination;
import com.furru.entity.Details;
import com.furru.entity.Itinerary;
import com.furru.exception.WanderLustException;
import com.furru.repository.DestinationRepository;

import com.furru.service.PackageServiceImpl;

@SpringBootTest
public class PackageServiceTest {
	
	@Mock
	DestinationRepository destinationRepository;
	
	@InjectMocks
	PackageServiceImpl packageServiceImpl;
	
	@Test
	public void invalidGetDestinationPacKages()  throws WanderLustException {
		List<Destination> empList=Collections.emptyList();
		DestinationDTO d= new DestinationDTO();
		d.setContinent("India");
		Mockito.when(destinationRepository.findByContinent("India")).thenReturn(empList);
		WanderLustException exception= Assertions.assertThrows(WanderLustException.class,()-> packageServiceImpl.getDestinationPackages(d.getContinent()));
		Assertions.assertEquals("DestinationService.CONTINENT_UNAVAILABLE",exception.getMessage());
		
	}
	@Test
	public void validGetDestinationPackages() throws WanderLustException {
		List<Destination> d=new LinkedList<>();
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
		d.add(d1);
		Mockito.when(destinationRepository.findByContinent("Europe")).thenReturn(d);
		List<DestinationDTO> d2=packageServiceImpl.getDestinationPackages("Europe");
		DestinationDTO d3=d2.get(0);
		Assertions.assertEquals("D1001", d3.getDestinationId());
		
	}
	@Test
	public void invalidGetHotDeals()  throws WanderLustException {
		List<Destination> empList=Collections.emptyList();
		Mockito.when(destinationRepository.findByDiscount()).thenReturn(empList);
		WanderLustException exception= Assertions.assertThrows(WanderLustException.class,()-> packageServiceImpl.getHotDeals());
		Assertions.assertEquals("DestinationService.HOTDEALS_UNAVAILABLE",exception.getMessage());
	}
	
	@Test
	public void validGetHotDeals() throws WanderLustException {
		List<Destination> d=new LinkedList<>();
		Destination dest=new Destination();
		dest.setDestinationId("D1001");
		dest.setContinent("Europe");
		dest.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
		dest.setImageUrl("/assets/prague.jpg");
		dest.setNoOfNights(7);
		dest.setFlightCharge(500.0f);
		dest.setChargePerPerson(2499.0f);
		dest.setDiscount(5.0f);
		dest.setAvailability(30);
		
		
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
		dest.setDetails(details);
		d.add(dest);
		Mockito.when(destinationRepository.findByDiscount()).thenReturn(d);
		List<DestinationDTO> d2=packageServiceImpl.getHotDeals();
		DestinationDTO d3=d2.get(0);
		Assertions.assertEquals("D1001", d3.getDestinationId());
	}
	
}
	
	
	
	
	


