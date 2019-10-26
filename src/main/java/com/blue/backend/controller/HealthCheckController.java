package com.blue.backend.controller;

import com.blue.backend.model.Health;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HealthCheckController {
    @RequestMapping(
            path = "/v1/health-check",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public Health healthCheck() {
        return new Health(true);
    }
}
