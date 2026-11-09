package com.wildtrail.controller;

import com.wildtrail.service.AdminService;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin"})
// AdminController: Controller for the WildTrail application.
public class AdminController {
    private final AdminService admin;

    public AdminController(AdminService admin) {
        this.admin = admin;
    }

    @GetMapping(value={"/dashboard"})
    @PreAuthorize(value="hasRole('ADMIN')")
    // Handles dashboard using the existing project logic.
    public Map<String, Object> dashboard() {
        return this.admin.dashboard();
    }
}
