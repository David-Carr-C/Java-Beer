package com.example.service.implementation;

import com.example.entity.UnitEntity;
import com.example.repository.IUnitRepository;
import com.example.service.IUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UnitService implements IUnitService {
    @Autowired
    private IUnitRepository unitRepository;

    @Override
    public Iterable<UnitEntity> getUnits() {
        return unitRepository.findAll();
    }

    @Override
    public UnitEntity getUnit(Long id) {
        Optional<UnitEntity> unitEntity = unitRepository.findById(id);
        return unitEntity.orElse(null);
    }

    @Override
    public UnitEntity postUnit(Object unitDTO) {
        return null;
    }

    @Override
    public UnitEntity putUnit(Long id, Object unitDTO) {
        return null;
    }

    @Override
    public String deleteUnit(Long id) {
        return null;
    }
}
