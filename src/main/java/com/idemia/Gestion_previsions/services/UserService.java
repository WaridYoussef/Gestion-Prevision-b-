package com.idemia.Gestion_previsions.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.idemia.Gestion_previsions.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	 UserDto CreateUser(UserDto userDto);
	
	 UserDto getUser(String email);
	
	 UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId,UserDto userDto);
	
	void DeleteUser(String userId);
	
	List<UserDto> getUsers(String userId);

	UserDto updateUserPW(String id, UserDto userDto);

	List<UserDto> getManagers();
	
}
