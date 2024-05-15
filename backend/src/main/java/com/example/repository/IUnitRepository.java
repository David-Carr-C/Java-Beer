package com.example.repository;

import com.example.entity.UnitEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUnitRepository<T extends UnitEntity> extends CrudRepository<T, Long>{
}
