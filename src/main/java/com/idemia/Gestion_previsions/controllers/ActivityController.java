package com.idemia.Gestion_previsions.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.idemia.Gestion_previsions.requests.ActivityRequest;
import com.idemia.Gestion_previsions.responses.ActivityResponse;
import com.idemia.Gestion_previsions.services.ActivityService;
import com.idemia.Gestion_previsions.shared.dto.ActivityDto;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	ActivityService activityService;

	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ActivityResponse> getActivity(@PathVariable(name = "id") long activityId) {

		ActivityDto activityDto = activityService.getActivity(activityId);
		ModelMapper modelmapper = new ModelMapper();
		ActivityResponse activityResponse = modelmapper.map(activityDto, ActivityResponse.class);
		return new ResponseEntity<>(activityResponse ,HttpStatus.OK); 
	}
	
	@RequestMapping("/activs/{id}")
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ActivityResponse>> getAllActivities(@PathVariable(name = "id") String userId){
		
		List<ActivityResponse> activitiesResponse = new ArrayList<>();
		
		List<ActivityDto> activities = activityService.getActivities(userId);
		
			for(ActivityDto activityDto : activities) {
			
			ModelMapper modemapper=new ModelMapper();
			ActivityResponse activity = modemapper.map(activityDto, ActivityResponse.class);
			
			activitiesResponse.add(activity);
		}
			return new ResponseEntity<>(activitiesResponse ,HttpStatus.OK);
		
	}
	

	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest) {

		ModelMapper modelMapper = new ModelMapper();
		
		ActivityDto activityDto = new ActivityDto();
		BeanUtils.copyProperties(activityRequest, activityDto);

		ActivityDto createActivity = activityService.createActivity(activityDto);

		ActivityResponse activityResponse = modelMapper.map(createActivity, ActivityResponse.class);

		return new ResponseEntity<ActivityResponse>(activityResponse ,HttpStatus.CREATED); 
	}

	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ActivityResponse> updateActivity(@PathVariable long id, @RequestBody ActivityRequest activityRequest) {

		ModelMapper modelMapper = new ModelMapper();
		
		ActivityDto activityDto = new ActivityDto();
		BeanUtils.copyProperties(activityRequest, activityDto);


		ActivityDto updateActivity = activityService.updateActivity(id, activityDto);

		ActivityResponse activityResponse = modelMapper.map(updateActivity, ActivityResponse.class);

		
		return new ResponseEntity<ActivityResponse>(activityResponse ,HttpStatus.ACCEPTED); 

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteActivity(@PathVariable long id) {
		activityService.DeleteActivity(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
