package com.wildtrail.controller;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/health"})
// HealthController: Controller for the WildTrail application.
public class HealthController {
    private final JdbcTemplate jdbc;

    public HealthController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping
    // Handles health using the existing project logic.
    public Map<String, Boolean> health() {
        this.jdbc.queryForObject("SELECT 1", Integer.class);
        return Map.of("ok", true);
    }
}
