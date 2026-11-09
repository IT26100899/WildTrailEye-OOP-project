package com.wildtrail.modules.users;

// User: Java class for the WildTrail application.
public abstract class User {
    private final int id;
    private final String email;
    private final String fullName;

    protected User(int id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    // Returns an existing value or related record.
    public int getId() {
        return this.id;
    }

    // Returns an existing value or related record.
    public String getEmail() {
        return this.email;
    }

    // Returns an existing value or related record.
    public String getFullName() {
        return this.fullName;
    }

    public abstract String getRole();

    public abstract String getHomePath();

    public abstract boolean canManageSystem();

    // Creates a new record using the existing validation and storage rules.
    public static User create(String role, int id, String email, String fullName) {
        return "ROLE_ADMIN".equals(role) ? new Admin(id, email, fullName) : new Customer(id, email, fullName);
    }
}
