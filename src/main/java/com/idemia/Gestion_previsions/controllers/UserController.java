package com.idemia.Gestion_previsions.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idemia.Gestion_previsions.exceptions.UserException;
import com.idemia.Gestion_previsions.requests.ChangePassRequest;
import com.idemia.Gestion_previsions.requests.UserRequest;
import com.idemia.Gestion_previsions.responses.ErrorMessages;
import com.idemia.Gestion_previsions.responses.UserResponse;
import com.idemia.Gestion_previsions.services.UserService;
import com.idemia.Gestion_previsions.shared.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);
		ModelMapper modelmapper = new ModelMapper();
		UserResponse userResponse = modelmapper.map(userDto, UserResponse.class);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@RequestMapping("/collabs/{id}")
	@GetMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserResponse>> getAllUsers(@PathVariable(name = "id") String userId) {

		List<UserResponse> usersResponse = new ArrayList<>();

		List<UserDto> users = userService.getUsers(userId);

		for (UserDto userDto : users) {

			ModelMapper modemapper = new ModelMapper();
			UserResponse user = modemapper.map(userDto, UserResponse.class);

			usersResponse.add(user);
		}

		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		if (userRequest.getFirstName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelmapper = new ModelMapper();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		UserDto createUser = userService.CreateUser(userDto);

		UserResponse userResponse = modelmapper.map(createUser, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	

	@RequestMapping("/changePassWord/{id}")
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUserMDP(@PathVariable String id, @RequestBody ChangePassRequest changePassRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(changePassRequest, userDto);

		UserDto updateUserPW = userService.updateUserPW(id, userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUserPW, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping("/managers")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserResponse>> getManagers() {

		List<UserResponse> usersResponse = new ArrayList<>();

		List<UserDto> users = userService.getManagers();

		for (UserDto userDto : users) {

			ModelMapper modemapper = new ModelMapper();
			UserResponse user = modemapper.map(userDto, UserResponse.class);

			usersResponse.add(user);
		}

		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
	}
	

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.DeleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
