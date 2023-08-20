package com.furru.api;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.furru.dto.BookingDTO;
import com.furru.dto.DestinationDTO;
import com.furru.service.BookingService;

//edit
@CrossOrigin
@RestController
@RequestMapping("BookingAPI")
public class BookingAPI {
	
@Autowired
private BookingService bookingService;
@Autowired
private Environment environment;

@RequestMapping(value="/booking",method=RequestMethod.POST)
public ResponseEntity<BookingDTO> getBookingPackage(@RequestBody BookingDTO bookingDTO){
	try {
		BookingDTO b=bookingService.getBookingPackage(bookingDTO);
		return new ResponseEntity<BookingDTO>(b,HttpStatus.OK);
		
	}catch(Exception e) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
	}
}
@RequestMapping(value="/booking/{destinationId}",method=RequestMethod.GET)
public ResponseEntity<DestinationDTO> getDestination(@PathVariable String destinationId){
	try {
		DestinationDTO h=bookingService.getDestination(destinationId);
		return new ResponseEntity<DestinationDTO>(h,HttpStatus.OK);
	}
	catch(Exception e) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()));
	}
}

}












