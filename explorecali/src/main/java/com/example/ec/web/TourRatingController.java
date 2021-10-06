package com.example.ec.web;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.demos.Tour;
import com.example.ec.demos.TourRating;
import com.example.ec.demos.TourRatingPk;
import com.example.ec.repos.TourRatingRepository;
import com.example.ec.repos.TourRepository;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

	
	TourRepository tourRepository;
	TourRatingRepository tourRatingRepository;
	/**
	 * @param tourRepository
	 * @param tourRatingRepository
	 */
	@Autowired
	public TourRatingController(TourRepository tourRepository, TourRatingRepository tourRatingRepository) {
		this.tourRepository = tourRepository;
		this.tourRatingRepository = tourRatingRepository;
	}
	
	/**
	 *Default constructor 
	 */
	protected TourRatingController() {
		// TODO Auto-generated constructor stub
	}

	//Method validates the request body and will return the Tour given a TourId
	//If not found, will end 404 Not Found
	/*
	 * Create a TourRating
	 * 
	 * @param tourId		tour identifier
	 * @param ratingDto		rating data transfer object
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable(value = "tourId") String tourId,
								@RequestBody @Validated TourRating tourRating) {
		Tour tour = verifyTour(tourId);
		//tourRatingRepository.save(new TourRating(new TourRatingPk(tour, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()));
		tourRatingRepository.save(new TourRating(tourId, tourRating.getCustomerId(),
                tourRating.getScore(), tourRating.getComment()));
	}
	
	/*
	 * Verify and return the Tour given a tourId
	 *  @param tourId	tour identifier.
	 *  @return			the found tour.
	 *  @throws			NoSuchElementException if no tour found.
	 */
	private Tour verifyTour(String tourId) throws NoSuchElementException{
		return tourRepository.findById(tourId).orElseThrow(
				() -> new NoSuchElementException("Tour does not exist " + tourId)				
				);
	}
	
	/*
	 * Exception handler if NoSuchElementException is thrown in this controller
	 * 
	 * @param ex	exception
	 * @return		Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex){
		return ex.getMessage();
	}
	
	//Page instead of List
	@GetMapping
	public Page<RatingDto> getAllRatingsForTour(@PathVariable(value="tourId") String tourId, Pageable pageable){
		verifyTour(tourId);
		/*
		 * return tourRatingRepository.findByPkTourId(tourId).stream()
		 * .map(RatingDto::new).collect(Collectors.toList());
		 */
		Page<TourRating> ratings = tourRatingRepository.findByTourId(tourId, pageable);
		return new PageImpl<>(
				ratings.get().map(RatingDto::new).collect(Collectors.toList()),
				pageable,
				ratings.getTotalElements()
				);
	}
	
	@GetMapping(path = "/average")
	public Map<String, Double> getAverage(@PathVariable(value="tourId") String tourId, Pageable pageable){
		verifyTour(tourId);
		return Map.of("average", tourRatingRepository.findByTourId(tourId, pageable).stream()
				.mapToInt(TourRating::getScore).average()
				.orElseThrow(()->
						new NoSuchElementException("Tour has no Ratings")					
						));
	}
	
	/*
	 * Verify and return the tourRating for a particular tourId and Customer
	 */
	private TourRating verifyTourRating(String tourId, int customerId) throws NoSuchElementException{
		return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId).orElseThrow(()->
				new NoSuchElementException("Tour-Rating pair for request("
						+tourId+" for customer " + customerId));
	}
	
	/**
     * Update score and comment of a Tour Rating
     *
     * @param tourId tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
	@PutMapping
	public RatingDto updateWithPut(@PathVariable(value="tourId") String tourId, @RequestBody @Validated RatingDto ratingDto) {
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
		rating.setScore(ratingDto.getScore());
		rating.setComment(ratingDto.getComment());
		return new RatingDto(tourRatingRepository.save(rating));
	}
	
	/**
     * Update score or comment of a Tour Rating
     *
     * @param tourId tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
	@PatchMapping
	public RatingDto updateWithPatch(@PathVariable(value="tourId") String tourId, 
			@RequestBody @Validated RatingDto ratingDto) {
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
		if(ratingDto.getScore() != null) {
			rating.setScore(ratingDto.getScore());
		}
		if(ratingDto.getComment() != null) {
			rating.setComment(ratingDto.getComment());
		}
		return new RatingDto(tourRatingRepository.save(rating));
	}
	
	/**
     * Delete a Rating of a tour made by a customer
     *
     * @param tourId tour identifier
     * @param customerId customer identifier
     */
	@DeleteMapping(path = "/{customerId}")
	public void delete(@PathVariable(value="tourId") String tourId, 
			@PathVariable(value = "customerId") int customerId) {
		TourRating rating = verifyTourRating(tourId, customerId);
		tourRatingRepository.delete(rating);
	}
}
