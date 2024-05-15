package com.example.controller;

import com.example.domain.HttpResponse;
import com.example.service.implementation.ActivityService;
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
@RequestMapping("/activity")
public class ActivityController {
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @GetMapping()
    public ResponseEntity<HttpResponse> getActivities() {
        logger.debug("getActivities() called");
        try {
            var data = activityService.getActivities();
            return ResponseEntity.ok(HttpResponse.getHttpResponse(of("activities", data), "Actividades Obtenidas", OK));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponse.getErrorHttpResponse(e.getMessage(), "e.getStackTrace()", "e.getStackTrace()", e.getStackTrace(), BAD_REQUEST), BAD_REQUEST);
        }
    }
}
