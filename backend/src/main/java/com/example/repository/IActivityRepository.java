package com.example.repository;

import com.example.entity.ActivityEntity;
import org.springframework.data.repository.CrudRepository;

public interface IActivityRepository<T extends ActivityEntity> extends CrudRepository<T, Long> {
}
