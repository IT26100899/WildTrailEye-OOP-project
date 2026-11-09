package com.wildtrail.modules.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Public team cards intentionally include only a name and role.
@RestController
// TeamController: Controller for the WildTrail application.
public class TeamController {
    private final FileGuideRepository repository;

    public TeamController(FileGuideRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/team")
    // Handles team using the existing project logic.
    public List<Map<String, String>> team() {
        List<Map<String, String>> team = new ArrayList<>();
        for (Guide guide : repository.findAll()) {
            if (guide.isAvailable()) {
                team.add(Map.of("name", guide.getFullName(), "role", guide.getAssignmentRole()));
            }
        }
        return team;
    }
}
