package com.furru.repository;

import com.furru.entity.Destination;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepository extends CrudRepository<Destination, String> {
	@Query(value="select d from Destination d where lower(d.continent)=lower(?1)")
	public List<Destination> findByContinent(String continent);
	@Query(value="select d from Destination d where d.discount>0.0")
	public List<Destination> findByDiscount();
	@Query(value="select d from Destination d where lower(d.destinationId)=lower(?1)")
	public Optional<Destination> findById(String destinationId);

}
