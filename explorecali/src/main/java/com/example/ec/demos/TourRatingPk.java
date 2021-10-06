//Not needed in mongodb implementation
package com.example.ec.demos;

import java.io.Serializable;

/*import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;*/


/*
 * Tour Rating Primary Key containing a Tour and a Customer Identifier
 * 
 */
//@Embeddable
public class TourRatingPk implements Serializable{

	//@ManyToOne
	private Tour tour;
	
	//@Column(insertable = false, updatable = false, nullable = false)
	private Integer customerId;
	
	public TourRatingPk() {
		
	}

	/**
	 *  Fully initialize a Tour Rating PK
	 * @param tour			the tour
	 * @param customerId	the customer identifier
	 */
	public TourRatingPk(Tour tour, Integer customerId) {
		this.tour = tour;
		this.customerId = customerId;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		int result = tour.hashCode();
		result = 31*result + customerId.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//If references are same
		if(this == obj)  return true;
		if(obj == null ||  getClass() != obj.getClass())
			return false;
		TourRatingPk  that = (TourRatingPk) obj;
		if(tour.equals(that.tour) && customerId.equals(that.customerId))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "TourRatingPk{" +
				"tour="+ tour +
				", cusomterId=" + customerId +
				"}";				
	}
	
	
	
}
