package com.wildtrail.security;

// SessionUser: configuration/security helper for the WildTrail application.
// Creates a new SessionUser object.
public record SessionUser(int uid, String email, String name, String role) {
}
