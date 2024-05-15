package com.example.controller;

import com.example.domain.HttpResponse;
import com.example.dto.OpticalFiberReportDTO;
import com.example.entity.OpticalFiberReportEntity;
import com.example.service.implementation.OpticalFiberReportService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/optical-fiber-report")
public class OpticalFiberReportController {
    private final Logger logger = LoggerFactory.getLogger(OpticalFiberReportController.class);

    @Autowired
    private OpticalFiberReportService opticalFiberReportService;

    @PostMapping()
    public ResponseEntity<HttpResponse> postOpticalFiberReport(@RequestBody @Valid OpticalFiberReportDTO opticalFiberReportDTO) {
        logger.debug("postOpticalFiberReport() called");
        try {
            var data = opticalFiberReportService.postOpticalFiberReport(opticalFiberReportDTO);
            return ResponseEntity.ok(HttpResponse.getHttpResponse(of("optical_fiber_report", data), "Reporte Creado", OK));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponse.getErrorHttpResponse(e.getMessage(), "e.getReason()", "e.getDeveloperMessage()", e.getStackTrace(), BAD_REQUEST), BAD_REQUEST);
        }
    }

    @PostMapping("/pdf/{id}")
    public ResponseEntity<HttpResponse> createReportPdf(@PathVariable Long id) {
        logger.debug("createReportPdf() called");
        try {
            var data = opticalFiberReportService.createReportPdf(id);
            return ResponseEntity.ok(HttpResponse.getHttpResponse(of("pdf", data), "Pdf Creado Con Éxito", OK));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponse.getErrorHttpResponse(e.getMessage(), "e.getReason()", "e.getDeveloperMessage()", e.getStackTrace(), BAD_REQUEST), BAD_REQUEST);
        }
    }
}
