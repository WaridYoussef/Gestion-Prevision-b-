package com.idemia.Gestion_previsions.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idemia.Gestion_previsions.entities.ActivityEntity;
import com.idemia.Gestion_previsions.repository.ActivityRepository;
import com.idemia.Gestion_previsions.services.ActivityService;
import com.idemia.Gestion_previsions.shared.dto.ActivityDto;

@Service
public class ActivityServiceImpl implements ActivityService {

	
	@Autowired
	ActivityRepository activityRepository;
	
	
	
	
	@Override
	public ActivityDto getActivity(long activityId) {
		ActivityEntity activityEntity = activityRepository.findById(activityId);
		ModelMapper modelmapper=new ModelMapper();
		ActivityDto activityDto = modelmapper.map(activityEntity, ActivityDto.class);
		return activityDto;
	}

	@Override
	public ActivityDto createActivity(ActivityDto activityDto) {
		 ModelMapper modelMapper=new ModelMapper();
	        ActivityEntity activityEntity=modelMapper.map(activityDto, ActivityEntity.class);
	        ActivityEntity newActivity=activityRepository.save(activityEntity);
	        ActivityDto activity=modelMapper.map(newActivity,ActivityDto.class);
		return activity;
	}

	@Override
	public ActivityDto updateActivity(long id, ActivityDto activityDto) {
		ActivityEntity activityEntity = activityRepository.findById(id);
		if(activityEntity == null) throw new RuntimeException("activity NOT FOUND !");
		
		activityEntity.setName(activityDto.getName());
		
		ActivityEntity upActivity = activityRepository.save(activityEntity);
		
		ModelMapper modelmapper=new ModelMapper();
		ActivityDto group = modelmapper.map(upActivity, ActivityDto.class);
		
		return group;
	}

	@Override
	public void DeleteActivity(long id) {
		ActivityEntity activityEntity = activityRepository.findById(id);
		if (activityEntity == null)
			throw new RuntimeException(id+" Not found!");
		activityRepository.delete(activityEntity);
		
	}

	@Override
	public List<ActivityDto> getActivities() {
		
		List<ActivityDto> activitiesDto = new ArrayList<>();
		
		List<ActivityEntity> activityPage = activityRepository.findAllActivities();

		List<ActivityEntity> activities = activityPage;
		for (ActivityEntity activityEntity : activities) {

			ModelMapper modemapper = new ModelMapper();
			ActivityDto activityDto = modemapper.map(activityEntity, ActivityDto.class);

			activitiesDto.add(activityDto);
		}

		return activitiesDto;
	}
	
	

}
