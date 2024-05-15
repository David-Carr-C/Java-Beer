package com.example.controller;

import com.example.domain.HttpResponse;
import com.example.service.implementation.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/unit")
public class UnitController {
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private UnitService unitService;

    @GetMapping()
    public ResponseEntity<HttpResponse> getUnits() {
        logger.debug("getUnits() called");
        try {
            var data = unitService.getUnits();
            return ResponseEntity.ok(HttpResponse.getHttpResponse(of("units", data), "Unidades Obtenidas", OK));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponse.getErrorHttpResponse(e.getMessage(), "e.getStackTrace()", "e.getStackTrace()", e.getStackTrace(), BAD_REQUEST), BAD_REQUEST);
        }
    }
}
