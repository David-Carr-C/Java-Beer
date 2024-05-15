package com.example.service;

import com.example.dto.OpticalFiberReportDTO;
import com.example.entity.OpticalFiberReportEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface IOpticalFiberReportService {
    OpticalFiberReportEntity postOpticalFiberReport(OpticalFiberReportDTO opticalFiberReportDTO);
    ResponseEntity<Resource> createReportPdf(Long id);
}
