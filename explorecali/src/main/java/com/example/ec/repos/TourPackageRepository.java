package com.example.ec.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.example.ec.demos.TourPackage;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends CrudRepository<TourPackage, String>{

	//Add a method to find an entry by name.
	//This is basically a query disguised as function
	Optional<TourPackage> findByName(String name);
	
	//Optional<TourPackage> findByCode(String code);

	
	@Override
	@RestResource(exported = false) 
	<S extends TourPackage> S save(S entity);

	@Override
	@RestResource(exported = false)
	<S extends TourPackage> Iterable<S> saveAll(Iterable<S> entities);

	@Override
	@RestResource(exported = false)
	void deleteById(String id);

	@Override
	@RestResource(exported = false)
	void delete(TourPackage entity);

	@Override
	@RestResource(exported = false)
	void deleteAllById(Iterable<? extends String> ids);

	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends TourPackage> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();
	 
	 
}
