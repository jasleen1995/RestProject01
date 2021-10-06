package com.example.ec.demos;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
*/
//@Entity
@Document
public class Tour {

	//@Id
	//@GeneratedValue
	//private Integer id;
	@Id
	private String id;
	
	//@Column
	@Indexed
	private String title;
	
	@Indexed
	private String tourPackageCode;
	
	private String tourPackageName;
	
	//Basically all the attributes which are commented, can be entered in a lis of details as key-value pair.
	private Map<String, String> details;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTourPackageCode() {
		return tourPackageCode;
	}

	public void setTourPackageCode(String tourPackageCode) {
		this.tourPackageCode = tourPackageCode;
	}

	public String getTourPackageName() {
		return tourPackageName;
	}

	public void setTourPackageName(String tourPackageName) {
		this.tourPackageName = tourPackageName;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	/**
	 * @param id
	 * @param title
	 * @param tourPackage
	 * @param tourPackageName
	 * @param details
	 */
	public Tour(String title, TourPackage tourPackage, Map<String, String> details) {
		 this.title = title;
	        this.tourPackageCode = tourPackage.getCode();
	        this.tourPackageName = tourPackage.getName();
	        this.details = details;
	}

	/**
	 * 
	 */
	protected Tour() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Tour{"+
				"id = '"+id+ "'"+
				", details = "+details +"}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Tour tour = (Tour) obj;
		return Objects.equals(id, tour.id) && 
				Objects.deepEquals(details, tour.details);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(id, details);
	}
	/*
	 * @Column(length = 2000) private String description;
	 * 
	 * @Column(length = 2000) private String blurb;
	 * 
	 * @Column private Integer price;
	 * 
	 * @Column private String duration;
	 */
	
	/*
	 * @Column(length = 2000) private String bullets;
	 * 
	 * @Column private String keywords;
	 * 
	 * @ManyToOne private TourPackage tourPackage;
	 * 
	 * //@Column //@Enumerated private Difficulty difficulty;
	 * 
	 * //@Column //@Enumerated private Region region;
	 */

	/*
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 * 
	 * public String getTitle() { return title; }
	 * 
	 * public void setTitle(String title) { this.title = title; }
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description =
	 * description; }
	 * 
	 * public String getBlurb() { return blurb; }
	 * 
	 * public void setBlurb(String blurb) { this.blurb = blurb; }
	 * 
	 * public Integer getPrice() { return price; }
	 * 
	 * public void setPrice(Integer price) { this.price = price; }
	 * 
	 * public String getDuration() { return duration; }
	 * 
	 * public void setDuration(String duration) { this.duration = duration; }
	 * 
	 * public String getBullets() { return bullets; }
	 * 
	 * public void setBullets(String bullets) { this.bullets = bullets; }
	 * 
	 * public String getKeywords() { return keywords; }
	 * 
	 * public void setKeywords(String keywords) { this.keywords = keywords; }
	 * 
	 * public TourPackage getTourPackage() { return tourPackage; }
	 * 
	 * public void setTourPackage(TourPackage tourPackage) { this.tourPackage =
	 * tourPackage; }
	 * 
	 * public Difficulty getDifficulty() { return difficulty; }
	 * 
	 * public void setDifficulty(Difficulty difficulty) { this.difficulty =
	 * difficulty; }
	 * 
	 * public Region getRegion() { return region; }
	 * 
	 * public void setRegion(Region region) { this.region = region; }
	 * 
	 * public Tour(String title, String description, String blurb, Integer price,
	 * String duration, String bullets, String keywords, TourPackage tourPackage,
	 * Difficulty difficulty, Region region) { super();
	 * 
	 * this.title = title; this.description = description; this.blurb = blurb;
	 * this.price = price; this.duration = duration; this.bullets = bullets;
	 * this.keywords = keywords; this.tourPackage = tourPackage; this.difficulty =
	 * difficulty; this.region = region; }
	 * 
	 * protected Tour() {
	 * 
	 * }
	 */
}
