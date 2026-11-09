package com.wildtrail.modules.users;

// Customer: Java class for the WildTrail application.
public final class Customer
extends User {
    public Customer(int id, String email, String fullName) {
        super(id, email, fullName);
    }

    @Override
    // Returns an existing value or related record.
    public String getRole() {
        return "ROLE_CUSTOMER";
    }

    @Override
    // Returns an existing value or related record.
    public String getHomePath() {
        return "/client/my-bookings";
    }

    @Override
    // Handles can manage system using the existing project logic.
    public boolean canManageSystem() {
        return false;
    }
}
