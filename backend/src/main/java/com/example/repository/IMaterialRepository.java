package com.example.repository;

import com.example.entity.MaterialEntity;
import org.springframework.data.repository.CrudRepository;

public interface IMaterialRepository<T extends MaterialEntity> extends CrudRepository<T, Long> {
}
