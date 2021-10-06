package com.example.ec.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.ec.demos.TourRating;

public class RatingDto {

	//TourId gonna be a parameter in the url.
	@Min(0)
	@Max(5)
	private Integer score;
	
	@Size(max = 255)
	private String comment;
	
	@NotNull
	private Integer customerId;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public RatingDto(TourRating tourRating) {
		//this(tourRating.getScore(), tourRating.getComment(), tourRating.getPk().getCustomerId());
	}

	/**
	 * @param score
	 * @param comment
	 * @param customerId
	 */
	private RatingDto(@Min(0) @Max(5) Integer score, @Size(max = 255) String comment, @NotNull Integer customerId) {
		this.score = score;
		this.comment = comment;
		this.customerId = customerId;
	}
	
}
