package com.wildtrail.modules.resources;

import java.util.List;

// HiluxSafariJeep: Java class for the WildTrail application.
public final class HiluxSafariJeep extends SafariJeep {
    public HiluxSafariJeep(int id, String name, int capacity, boolean fourByFour, List<String> features, boolean available, double price, String imageUrl) {
        super(id, name, capacity, fourByFour, features, available, price, imageUrl);
    }

    @Override
    // Returns an existing value or related record.
    public String getCategory() {
        return "HILUX";
    }

    @Override
    // Returns an existing value or related record.
    public String getCategoryLabel() {
        return "Toyota Hilux";
    }

    @Override
    // Handles standard features using the existing project logic.
    protected List<String> standardFeatures() {
        List<String> result = super.standardFeatures();
        result.add("Smooth suspension");
        return result;
    }
}
