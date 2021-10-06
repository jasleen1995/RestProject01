package com.example.ec.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ec.demos.TourRating;
import com.example.ec.demos.TourRatingPk;

@RepositoryRestResource(exported = false)
//public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk>{
public interface TourRatingRepository extends CrudRepository<TourRating, String>{

	/*
	 * Lookup all the TourRatings for a tour.
	 * 
	 * @param tourId is the Tour Identifier
	 * @return a List of any found TourRatings 
	 * 
	 */
	
	//List<TourRating> findByPkTourId(Integer tourId);
	List<TourRating> findByTourId(String tourId);
	/*
	 * Lookup a TourRating by the TourId and CustomerId
	 * @param tourId tour Identifier
	 * @param customerId customer identifier
	 * @return Optional of found TourRatings
	 */
	
	//Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
	Optional<TourRating> findByTourIdAndCustomerId(String tourId, Integer customerId);
	
	//Page<TourRating> findByPkTourId(Integer tourId, Pageable pageable);
	Page<TourRating> findByTourId(String tourId, Pageable pageable);
}
