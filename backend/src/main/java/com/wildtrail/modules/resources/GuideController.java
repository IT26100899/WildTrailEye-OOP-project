package com.wildtrail.modules.resources;

import com.wildtrail.exception.ApiException;
import com.wildtrail.util.RequestValues;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// Admin-only endpoints for guide CRUD and guide assignments.
@RestController
@RequestMapping("/api/guides")
@PreAuthorize("hasRole('ADMIN')")
// GuideController: Controller for the WildTrail application.
public class GuideController {
    private final FileGuideRepository repository;
    private final JdbcTemplate jdbc;

    public GuideController(FileGuideRepository repository, JdbcTemplate jdbc) {
        this.repository = repository;
        this.jdbc = jdbc;
    }

    @GetMapping
    // Handles all using the existing project logic.
    public List<Guide> all() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Creates a new record using the existing validation and storage rules.
    public Guide create(@RequestBody Map<String, Object> body) {
        return repository.save(0, parse(body));
    }

    @PutMapping("/{id}")
    @Transactional
    // Updates existing data without changing the stored format.
    public synchronized Guide update(@PathVariable int id, @RequestBody Map<String, Object> body) {
        lockSchedule();
        Guide guide = parse(body);
        if (!guide.isAvailable() && activeAssignments(id) > 0) {
            throw ApiException.conflict("Reassign active safaris before marking the guide unavailable");
        }
        return repository.save(id, guide);
    }

    @DeleteMapping("/{id}")
    @Transactional
    // Removes an item according to existing rules.
    public synchronized Map<String, Boolean> remove(@PathVariable int id) {
        lockSchedule();
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM booking_guide_assignments WHERE guide_id=?", Integer.class, id);
        if (count != null && count > 0) {
            throw ApiException.conflict("Guide has assignment history. Mark inactive instead.");
        }
        repository.delete(id);
        return Map.of("ok", true);
    }

    @GetMapping("/assignments")
    // Handles assignments using the existing project logic.
    public List<Map<String, Object>> assignments() {
        return jdbc.queryForList(
                "SELECT booking_id AS \"bookingId\", guide_id AS \"guideId\" FROM booking_guide_assignments");
    }

    // Assign a guide only if the guide and booking are available on that date.
    @PostMapping("/assignments")
    @Transactional
    // Handles assign using the existing project logic.
    public synchronized Map<String, Boolean> assign(@RequestBody Map<String, Object> body) {
        lockSchedule();
        int bookingId = RequestValues.integer(body, "bookingId", 1, Integer.MAX_VALUE);
        int guideId = RequestValues.integer(body, "guideId", 1, Integer.MAX_VALUE);

        Guide guide = findGuide(guideId);
        if (!guide.isAvailable()) {
            throw ApiException.conflict("Guide is unavailable");
        }

        List<Map<String, Object>> bookings = jdbc.queryForList(
                "SELECT safari_date, status FROM bookings WHERE id=? FOR UPDATE", bookingId);
        if (bookings.isEmpty()) {
            throw ApiException.notFound("Booking was not found");
        }
        Map<String, Object> booking = bookings.get(0);
        if (!Set.of("PENDING", "CONFIRMED").contains(booking.get("status"))) {
            throw ApiException.conflict("Only active bookings can be assigned");
        }
        checkGuideSchedule(guideId, bookingId, booking.get("safari_date"));

        int updated = jdbc.update(
                "UPDATE booking_guide_assignments SET guide_id=? WHERE booking_id=?", guideId, bookingId);
        if (updated == 0) {
            jdbc.update("INSERT INTO booking_guide_assignments(booking_id,guide_id) VALUES (?,?)",
                    bookingId, guideId);
        }
        return Map.of("ok", true);
    }

    private Guide findGuide(int id) {
        for (Guide guide : repository.findAll()) {
            if (guide.getId() == id) {
                return guide;
            }
        }
        throw ApiException.notFound("Guide was not found");
    }

    private void checkGuideSchedule(int guideId, int bookingId, Object date) {
        String sql = "SELECT COUNT(*) FROM booking_guide_assignments a " +
                "JOIN bookings b ON b.id=a.booking_id " +
                "WHERE a.guide_id=? AND b.safari_date=? " +
                "AND b.status<>'CANCELLED' AND b.id<>?";
        Integer count = jdbc.queryForObject(sql, Integer.class, guideId, date, bookingId);
        if (count != null && count > 0) {
            throw ApiException.conflict("Guide already has a safari that day");
        }
    }

    private void lockSchedule() {
        jdbc.queryForObject("SELECT id FROM guide_schedule_lock WHERE id=1 FOR UPDATE", Integer.class);
    }

    private int activeAssignments(int id) {
        String sql = "SELECT COUNT(*) FROM booking_guide_assignments a " +
                "JOIN bookings b ON b.id=a.booking_id " +
                "WHERE a.guide_id=? AND b.status IN ('PENDING','CONFIRMED')";
        return jdbc.queryForObject(sql, Integer.class, id);
    }

    private Guide parse(Map<String, Object> body) {
        String name = RequestValues.text(body, "fullName", 120);
        String phone = RequestValues.text(body, "phone", 40);
        String license = RequestValues.text(body, "licenseNumber", 60);
        String specialty = RequestValues.text(body, "specialty", 20);
        boolean available = RequestValues.bool(body, "available", true);
        return Guide.create(0, name, phone, license, specialty, available);
    }
}
