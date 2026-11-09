package com.wildtrail.modules.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// Vehicle CRUD endpoints. Only admins can edit the fleet.
@RestController
@RequestMapping("/api")
// VehicleController: Controller for the WildTrail application.
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/vehicles")
    public List<Map<String, Object>> vehicles(@RequestParam(defaultValue = "0") String all,
                                              Authentication authentication) {
        boolean showAll = "1".equals(all) && isAdmin(authentication);
        List<Map<String, Object>> vehicles = service.vehicles(!showAll);
        if (showAll) {
            return vehicles;
        }

        // Never expose owner contact details in the public vehicle list.
        List<Map<String, Object>> publicList = new ArrayList<>();
        for (Map<String, Object> vehicle : vehicles) {
            Map<String, Object> safe = new LinkedHashMap<>(vehicle);
            safe.remove("ownerName");
            safe.remove("ownerPhone");
            safe.remove("ownerAddress");
            safe.remove("ownerIdNumber");
            publicList.add(safe);
        }
        return publicList;
    }

    @PostMapping("/vehicles")
    @PreAuthorize("hasRole('ADMIN')")
    // Creates a new record using the existing validation and storage rules.
    public ResponseEntity<Map<String, Object>> createVehicle(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVehicle(body));
    }

    @PutMapping("/vehicles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    // Updates existing data without changing the stored format.
    public Map<String, Object> updateVehicle(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return service.updateVehicle(id, body);
    }

    @DeleteMapping("/vehicles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    // Deletes a matching record according to existing rules.
    public Map<String, Boolean> deleteVehicle(@PathVariable int id) {
        service.deleteVehicle(id);
        return Map.of("ok", true);
    }

    private boolean isAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(role -> "ROLE_ADMIN".equals(role.getAuthority()));
    }
}
