package com.example.ec.demos;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
/*import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
*/
import org.springframework.data.mongodb.core.mapping.Document;


//@Entity
@Document
public class TourRating {

	//@EmbeddedId
//	private TourRatingPk pk;
	
	@Id
	private String id;
	
	private String tourId;
	
	@NotNull
	private Integer customerId;
	
	//@Column(nullable = false)
	@Min(0)
	@Max(5)
	private Integer score;
	
	//@Column
	@Size(max=255)
	private String comment;

	/*
	 * Create a fully initialized TourRating
	 * 
	 * @param pk 		primary key of a tour and customer id.
	 * @param score 	Integer score (1-5)
	 * @param comment 	Optional comment from the customer 
	 * 
	 */
	/*
	 * public TourRating(TourRatingPk pk, Integer score, String comment) { super();
	 * this.pk = pk; this.score = score; this.comment = comment; }
	 */
	
	public TourRating(String tourId, @NotNull Integer customerId, @Min(0) @Max(5) Integer score,
			@Size(max = 255) String comment) {
		//this.id = id;
		this.tourId = tourId;
		this.customerId = customerId;
		this.score = score;
		this.comment = comment;
	}
	protected TourRating() {
	}


	@Override
	public int hashCode() {
		//It return a hashcode of the current object
		//return Objects.hash(pk, score, comment);
		return Objects.hash(id, tourId, customerId, score, comment);
		
	}

	@Override
	public boolean equals(Object obj) {
		//If the reference values are same then they are equal
		if(this == obj) return true;
		
		//If the coming/parameter object is null, then return false, for obvious reasons.
		if(obj == null || getClass() != obj.getClass()) return false;
	
		//Now checking on one more usecase.
		//If the reference variables are different, then we can check the values of both the objects.
		TourRating that = (TourRating) obj;
		//return Objects.equals(pk, that.pk) &&
		return Objects.equals(id, that.id) &&
				Objects.equals(tourId, that.tourId) &&
				Objects.equals(customerId, that.customerId) &&
				Objects.equals(score, that.score) &&
				Objects.equals(comment, that.comment);
	}

	@Override
	public String toString() {
		return "TourRating{" +
				//"pk=" + pk +
				", id=" + id +
				", tourId=" + tourId +
				", customerId=" + customerId +
				", score=" + score +
				", comment='" + comment + "'" +
				"}";
		//Putting comment in quotes
	}

	/*
	 * public TourRatingPk getPk() { return pk; }
	 * 
	 * public void setPk(TourRatingPk pk) { this.pk = pk; }
	 */

	
	
	public Integer getScore() {
		return score;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTourId() {
		return tourId;
	}
	public void setTourId(String tourId) {
		this.tourId = tourId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
		
}
