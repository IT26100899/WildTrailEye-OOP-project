package com.wildtrail.modules.resources;

import java.util.List;

// LuxuryJeep: Java class for the WildTrail application.
public final class LuxuryJeep extends Vehicle {
    public LuxuryJeep(int id, String name, int capacity, boolean fourByFour, List<String> features, boolean available, double price, String imageUrl) {
        super(id, name, capacity, fourByFour, features, available, price, imageUrl);
    }

    @Override
    // Returns an existing value or related record.
    public String getCategory() {
        return "LUXURY";
    }

    @Override
    // Returns an existing value or related record.
    public String getCategoryLabel() {
        return "Luxury Jeep";
    }

    @Override
    // Handles standard features using the existing project logic.
    protected List<String> standardFeatures() {
        List<String> result = super.standardFeatures();
        result.add("Complimentary refreshments");
        result.add("Personal concierge");
        return result;
    }

    @Override
    // Calculates the value using the existing formula.
    public double calculateCost(int durationHours) {
        return super.calculateCost(durationHours) + 20.0;
    }
}
