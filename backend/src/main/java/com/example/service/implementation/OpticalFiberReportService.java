package com.example.service.implementation;

import com.example.dto.OpticalFiberReportDTO;
import com.example.dto.mapper.OpticalFiberReportDTOMapper;
import com.example.entity.OpticalFiberReportEntity;
import com.example.repository.IOpticalFiberReportRepository;
import com.example.service.IOpticalFiberReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpticalFiberReportService implements IOpticalFiberReportService {
    @Autowired
    private IOpticalFiberReportRepository opticalFiberReportRepository;

    @Override
    public OpticalFiberReportEntity postOpticalFiberReport(OpticalFiberReportDTO opticalFiberReportDTO) {
        OpticalFiberReportEntity entity = OpticalFiberReportDTOMapper.toOpticalFiberReport(opticalFiberReportDTO);
        opticalFiberReportRepository.save(entity);
        return entity;
    }

    @Override
    public ResponseEntity<Resource> createReportPdf(Long id) {
        opticalFiberReportRepository.findById(id);
        return null;
    }
}
