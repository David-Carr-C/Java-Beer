package com.example.repository;

import com.example.entity.OpticalFiberReportEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOpticalFiberReportRepository<T extends OpticalFiberReportEntity> extends CrudRepository<T, Long>{
}
