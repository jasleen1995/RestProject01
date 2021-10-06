package com.example.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.demos.TourPackage;
import com.example.ec.repos.TourPackageRepository;

@Service
public class TourPackageService {

	private TourPackageRepository tourPackageRepository;
	
	@Autowired
	public TourPackageService(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}
	
	/*
	 * @param code
	 * @param name 
	 */
	
	public TourPackage createTourPackage(String code, String name) {
		//Checks the existing TourPackage and creates a new if it does not exist
		
		return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
	}
	
	/*
	 * @param all tour packages
	 */ 
	
	public Iterable<TourPackage> lookup(){
		return tourPackageRepository.findAll();
	}
	 
	public long total() {
		return tourPackageRepository.count();
	}
}
