package com.furru.service;


import java.util.LinkedList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furru.dto.DestinationDTO;
import com.furru.dto.DetailsDTO;
import com.furru.dto.ItineraryDTO;
import com.furru.entity.Destination;
import com.furru.exception.WanderLustException;
import com.furru.repository.DestinationRepository;

@Service(value="packageService")
@Transactional
public class PackageServiceImpl implements PackageService {

	@Autowired
	DestinationRepository destinationRepository;
	
	@Override
	public List<DestinationDTO> getDestinationPackages(String continent) throws WanderLustException{
List<Destination> destination=destinationRepository.findByContinent(continent);
		
		
		
		if(destination.isEmpty())
		{
			throw new WanderLustException("DestinationService.CONTINENT_UNAVAILABLE");
			
		}
		List<DestinationDTO> destinationDTO= new LinkedList<>();
		destination.forEach(d->{
			DestinationDTO d1=new DestinationDTO();
			d1.setDestinationId(d.getDestinationId());
			d1.setContinent(d.getContinent());
			d1.setDestinationName(d.getDestinationName());
			d1.setImageUrl(d.getImageUrl());
			d1.setNoOfNights(d.getNoOfNights());
			d1.setFlightCharge(d.getFlightCharge());
			d1.setChargePerPerson(d.getChargePerPerson());
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
			destinationDTO.add(d1);
			
		});
		return destinationDTO;
	}
	@Override
	public List<DestinationDTO> getHotDeals() throws WanderLustException {

		List<Destination> destination=destinationRepository.findByDiscount();
		if(destination.isEmpty())
		{
			throw new WanderLustException("DestinationService.HOTDEALS_UNAVAILABLE");
			
		}
		List<DestinationDTO> destinationDTO= new LinkedList<>();
		destination.forEach(d->{
			DestinationDTO d1=new DestinationDTO();
			d1.setDestinationId(d.getDestinationId());
			d1.setContinent(d.getContinent());
			d1.setDestinationName(d.getDestinationName());
			d1.setImageUrl(d.getImageUrl());
			d1.setNoOfNights(d.getNoOfNights());
			d1.setFlightCharge(d.getFlightCharge());
			d1.setChargePerPerson(d.getChargePerPerson());
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
			destinationDTO.add(d1);
			
		});
		return destinationDTO;
	}
	
}
