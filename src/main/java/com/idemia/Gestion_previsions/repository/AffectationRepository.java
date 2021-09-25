package com.idemia.Gestion_previsions.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idemia.Gestion_previsions.entities.AffectationEntity;

@Repository
public interface AffectationRepository extends PagingAndSortingRepository<AffectationEntity, Long>{

	AffectationEntity findById(long affectationId);

	@Query(value = "SELECT * FROM affectation a WHERE a.user_id=:user_id" ,nativeQuery = true)
	List<AffectationEntity> findAllByUser_id(@Param("user_id") long user_id);
	
	@Query(value = "SELECT * FROM affectation  WHERE user_id=:user_id  ORDER BY id DESC" ,nativeQuery = true)
	List<AffectationEntity> findAllByUser_Idd(@Param("user_id") long user_id);
	
	@Query(value = "SELECT * FROM affectation" ,nativeQuery = true)
	List<AffectationEntity> findAll();

	
	
	
}
