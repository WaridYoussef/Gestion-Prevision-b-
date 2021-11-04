package com.idemia.Gestion_previsions.services;

import java.util.List;

import com.idemia.Gestion_previsions.shared.dto.ActivityDto;

public interface ActivityService {

	ActivityDto getActivity(long activityId);

	ActivityDto createActivity(ActivityDto activityDto);

	ActivityDto updateActivity(long id, ActivityDto activityDto);

	void DeleteActivity(long id);

	List<ActivityDto> getActivities(String userId);

	
}
