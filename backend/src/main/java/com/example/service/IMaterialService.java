package com.example.service;

import com.example.entity.MaterialEntity;

public interface IMaterialService {
    Iterable<MaterialEntity> getMaterials();
    MaterialEntity getMaterial(Long id);
    MaterialEntity postMaterial(Object materialDTO);
    MaterialEntity putMaterial(Long id, Object materialDTO);
    String deleteMaterial(Long id);
}
