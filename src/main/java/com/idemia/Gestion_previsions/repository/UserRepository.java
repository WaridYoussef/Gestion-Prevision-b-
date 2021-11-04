package com.idemia.Gestion_previsions.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idemia.Gestion_previsions.entities.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);
	
	UserEntity findById(long id);
	
	@Query(value = "SELECT * FROM user WHERE admin!=true AND manager_id=:user_id ORDER BY id DESC", nativeQuery = true)
	List<UserEntity> findAllUsers(@Param("user_id") String user_id);

	@Query(value = "SELECT * FROM user WHERE admin=true", nativeQuery = true)
	List<UserEntity> findAllManagers();
	
	
	
}
