package com.example.service;

import com.example.entity.UnitEntity;

public interface IUnitService {
    Iterable<UnitEntity> getUnits();
    UnitEntity getUnit(Long id);
    UnitEntity postUnit(Object unitDTO);
    UnitEntity putUnit(Long id, Object unitDTO);
    String deleteUnit(Long id);
}
