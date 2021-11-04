package com.idemia.Gestion_previsions.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.idemia.Gestion_previsions.entities.UserEntity;
import com.idemia.Gestion_previsions.repository.UserRepository;
import com.idemia.Gestion_previsions.services.UserService;
import com.idemia.Gestion_previsions.shared.dto.UserDto;
import com.idemia.Gestion_previsions.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto CreateUser(UserDto user) {

		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("User Already Exist !");
		ModelMapper modelmapper = new ModelMapper();
		UserEntity userEntity = modelmapper.map(user, UserEntity.class);
		
		userEntity.setUserId(util.generateStringId(30));
		userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = modelmapper.map(newUser, UserDto.class);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedpassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException(userId);
		ModelMapper modelmapper = new ModelMapper();
		UserDto userDto = modelmapper.map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setAdmin(userDto.getAdmin());

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	@Override
	public void DeleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException(userId);
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(String id) {

		

		List<UserDto> usersDto = new ArrayList<>();
		
		List<UserEntity> userPage;
		
			 userPage = userRepository.findAllUsers(id);
		
		

		List<UserEntity> users = userPage;
		for (UserEntity userEntity : users) {

			ModelMapper modemapper = new ModelMapper();
			UserDto userDto = modemapper.map(userEntity, UserDto.class);

			usersDto.add(userDto);
		}

		return usersDto;
	}

	@Override
	public UserDto updateUserPW(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new RuntimeException(id);
		
		// if currentPass = encryptedPass
		boolean result = bCryptPasswordEncoder.matches(userDto.getCurrentPass(), userEntity.getEncryptedpassword());
		
		if(result == false)
			throw new RuntimeException("Error password");
		
		userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(userDto.getNewPass()));
		

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
		
	}

	@Override
	public List<UserDto> getManagers() {
		
List<UserDto> usersDto = new ArrayList<>();
		
		List<UserEntity> userPage;
		
			 userPage = userRepository.findAllManagers();
		
		

		List<UserEntity> users = userPage;
		for (UserEntity userEntity : users) {

			ModelMapper modemapper = new ModelMapper();
			UserDto userDto = modemapper.map(userEntity, UserDto.class);

			usersDto.add(userDto);
		}

		return usersDto;
		
	}

}
