package com.wildtrail.modules.resources;

import java.util.List;

// SafariJeep: Java class for the WildTrail application.
public class SafariJeep extends Vehicle {
    public SafariJeep(int id, String name, int capacity, boolean fourByFour, List<String> features, boolean available, double price, String imageUrl) {
        super(id, name, capacity, fourByFour, features, available, price, imageUrl);
    }

    @Override
    // Returns an existing value or related record.
    public String getCategory() {
        return "STANDARD";
    }

    @Override
    // Returns an existing value or related record.
    public String getCategoryLabel() {
        return "Safari Jeep";
    }

    @Override
    // Handles standard features using the existing project logic.
    protected List<String> standardFeatures() {
        List<String> result = super.standardFeatures();
        result.add("Open-top viewing");
        return result;
    }
}
