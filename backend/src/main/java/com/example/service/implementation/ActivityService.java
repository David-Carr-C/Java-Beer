package com.example.service.implementation;

import com.example.entity.ActivityEntity;
import com.example.repository.IActivityRepository;
import com.example.service.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ActivityService implements IActivityService {
    @Autowired
    private IActivityRepository activityRepository;

    @Override
    public Iterable<ActivityEntity> getActivities() {
        return activityRepository.findAll();
    }

    @Override
    public ActivityEntity getActivity(Long id) {
        Optional<ActivityEntity> activityEntity = activityRepository.findById(id);
        return activityEntity.orElse(null);
    }

    @Override
    public ActivityEntity postActivity(Object activityDTO) {
        return null;
    }

    @Override
    public ActivityEntity putActivity(Long id, Object activityDTO) {
        return null;
    }

    @Override
    public String deleteActivity(Long id) {
        return null;
    }
}
