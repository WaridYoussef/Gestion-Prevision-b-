package com.idemia.Gestion_previsions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idemia.Gestion_previsions.entities.ActivityEntity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<ActivityEntity, Long>{

	ActivityEntity findById(long activityId);
	
	@Query(value = "SELECT * FROM activity WHERE manager_id=:user_id ORDER BY id DESC" ,nativeQuery = true)
	List<ActivityEntity> findAllActivities(@Param("user_id") String user_id);

	@Query(value = "SELECT * FROM activity WHERE name=:name AND manager_id=:manager_id" ,nativeQuery = true)
	ActivityEntity findByName(@Param("name") String name, @Param("manager_id") String manager_id);
	
	
}
