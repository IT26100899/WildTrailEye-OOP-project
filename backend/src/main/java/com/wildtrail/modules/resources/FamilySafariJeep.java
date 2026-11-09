package com.wildtrail.modules.resources;

import java.util.List;

// FamilySafariJeep: Java class for the WildTrail application.
public final class FamilySafariJeep extends SafariJeep {
    public FamilySafariJeep(int id, String name, int capacity, boolean fourByFour, List<String> features, boolean available, double price, String imageUrl) {
        super(id, name, capacity, fourByFour, features, available, price, imageUrl);
    }

    @Override
    // Returns an existing value or related record.
    public String getCategory() {
        return "FAMILY";
    }

    @Override
    // Returns an existing value or related record.
    public String getCategoryLabel() {
        return "Family Jeep";
    }

    @Override
    // Handles standard features using the existing project logic.
    protected List<String> standardFeatures() {
        List<String> result = super.standardFeatures();
        result.add("Kid-safe railings");
        return result;
    }
}
