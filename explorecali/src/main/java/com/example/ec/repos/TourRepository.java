package com.example.ec.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.example.ec.demos.Tour;
import com.example.ec.demos.TourPackage;

//public interface TourRepository extends PagingAndSortingRepository<Tour, Integer>{
public interface TourRepository extends PagingAndSortingRepository<Tour, String>{
	
	//List<Tour> findByTourPackageCode(@Param("code") String code);
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

	//New search endpoint /summary
	@Query(value="{'tourPackageCode' : ?0 }",
			fields = "{ 'id':1, 'title': 1, 'tourPackageCode':1, 'tourPackageName':1}")
	Page<Tour> findSummaryByTourPackageCode(@Param("code")String code, Pageable pageable);
	
	
	@Override
	@RestResource(exported = false)
	<S extends Tour> S save(S entity);

	@Override
	@RestResource(exported = false)
	<S extends Tour> Iterable<S> saveAll(Iterable<S> entities);

	@Override
	@RestResource(exported = false)
	//void deleteById(Integer id);
	void deleteById(String id);

	@Override
	@RestResource(exported = false)
	void delete(Tour entity);

	@Override
	@RestResource(exported = false)
	//void deleteAllById(Iterable<? extends Integer> ids);
	void deleteAllById(Iterable<? extends String> ids);
	
	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends Tour> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();
	
}
