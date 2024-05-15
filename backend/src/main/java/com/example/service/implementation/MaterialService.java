package com.example.service.implementation;

import com.example.entity.MaterialEntity;
import com.example.repository.IMaterialRepository;
import com.example.service.IMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MaterialService implements IMaterialService {
    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public Iterable<MaterialEntity> getMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public MaterialEntity getMaterial(Long id) {
        Optional<MaterialEntity> materialEntity = materialRepository.findById(id);
        return materialEntity.orElse(null);
    }

    @Override
    public MaterialEntity postMaterial(Object materialDTO) {
        return null;
    }

    @Override
    public MaterialEntity putMaterial(Long id, Object materialDTO) {
        return null;
    }

    @Override
    public String deleteMaterial(Long id) {
        return null;
    }
}
