package com.example.service;

import com.example.entity.ActivityEntity;

public interface IActivityService {
    Iterable<ActivityEntity> getActivities();
    ActivityEntity getActivity(Long id);
    ActivityEntity postActivity(Object activityDTO);
    ActivityEntity putActivity(Long id, Object activityDTO);
    String deleteActivity(Long id);
}
