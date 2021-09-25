package com.idemia.Gestion_previsions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.idemia.Gestion_previsions.entities.ActivityEntity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<ActivityEntity, Long>{

	ActivityEntity findById(long activityId);
	
	@Query(value = "SELECT * FROM activity ORDER BY id DESC" ,nativeQuery = true)
	List<ActivityEntity> findAllActivities();
	
	
}
