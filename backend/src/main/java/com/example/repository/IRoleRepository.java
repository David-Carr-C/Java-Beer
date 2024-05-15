package com.example.repository;

import com.example.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository<T extends RoleEntity> extends CrudRepository<T, Long> {
}
