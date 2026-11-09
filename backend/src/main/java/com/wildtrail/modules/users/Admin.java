package com.wildtrail.modules.users;

// Admin: Java class for the WildTrail application.
public final class Admin
extends User {
    public Admin(int id, String email, String fullName) {
        super(id, email, fullName);
    }

    @Override
    // Returns an existing value or related record.
    public String getRole() {
        return "ROLE_ADMIN";
    }

    @Override
    // Returns an existing value or related record.
    public String getHomePath() {
        return "/admin/dashboard";
    }

    @Override
    // Handles can manage system using the existing project logic.
    public boolean canManageSystem() {
        return true;
    }
}
