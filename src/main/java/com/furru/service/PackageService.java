package com.furru.service;

import java.util.List;

import com.furru.dto.DestinationDTO;
import com.furru.exception.WanderLustException;

public interface PackageService {
	public List<DestinationDTO> getDestinationPackages(String continent) throws WanderLustException;
	public List<DestinationDTO> getHotDeals() throws WanderLustException;
}
