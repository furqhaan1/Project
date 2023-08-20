package com.furru.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import com.furru.dto.DestinationDTO;

import com.furru.service.PackageService;


@CrossOrigin
@RestController
@RequestMapping(value ="/PackageAPI" )
public class PackageAPI {
	@Autowired
	private PackageService packageService; 
	@Autowired
	private Environment environment;

	// Retrieve all packages searched    
		/*@RequestMapping(value="/viewpackage/{destinationId}", method=RequestMethod.GET)
		public ResponseEntity <DestinationDTO> viewPackageDetails(@PathVariable String destinationId) throws WanderLustException{
			
			DestinationDTO destinationDTO=packageService.getHotDeals(destinationId);
			
			return new ResponseEntity<DestinationDTO>(destinationDTO, HttpStatus.OK);
			
		}*/
		@GetMapping(value="/packages/{continent}")
		public ResponseEntity<List<DestinationDTO>> getDestinationPackage(@PathVariable String continent){
			try {
				List<DestinationDTO> destinationDTO = packageService.getDestinationPackages(continent);
				ResponseEntity<List<DestinationDTO>>response =  new ResponseEntity<>(destinationDTO,HttpStatus.OK);
				return response;
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,environment.getProperty(e.getMessage()));
			}
			
		}
		
		@RequestMapping(value="/hotDeals",method=RequestMethod.GET)
		public ResponseEntity<List<DestinationDTO>> getHotDeal(){
			try {
				List<DestinationDTO> destination = packageService.getHotDeals();
				
				return new ResponseEntity<List<DestinationDTO>>(destination, HttpStatus.OK);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
			}
		
		}

	
		

}
