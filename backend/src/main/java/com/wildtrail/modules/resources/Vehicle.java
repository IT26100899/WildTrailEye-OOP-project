package com.wildtrail.modules.resources;

import com.wildtrail.exception.ApiException;
import java.util.ArrayList;
import java.util.List;

// Vehicle: Java class for the WildTrail application.
public abstract class Vehicle {
    private final int id;
    private final String name;
    private final int capacity;
    private final boolean fourByFour;
    private final List<String> features;
    private final boolean available;
    private final double price;
    private final String imageUrl;

    protected Vehicle(int id, String name, int capacity, boolean fourByFour, List<String> features, boolean available, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.fourByFour = fourByFour;
        this.features = List.copyOf(features); // Keep an independent, unmodifiable feature list.
        this.available = available;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Returns an existing value or related record.
    public int getId() {
        return this.id;
    }

    // Returns an existing value or related record.
    public String getName() {
        return this.name;
    }

    // Returns an existing value or related record.
    public int getCapacity() {
        return this.capacity;
    }

    // Checks a condition and returns true or false.
    public boolean isFourByFour() {
        return this.fourByFour;
    }

    // Returns an existing value or related record.
    public List<String> getFeatures() {
        return this.features;
    }

    // Checks a condition and returns true or false.
    public boolean isAvailable() {
        return this.available;
    }

    // Returns an existing value or related record.
    public double getPrice() {
        return this.price;
    }

    // Returns an existing value or related record.
    public String getImageUrl() {
        return this.imageUrl;
    }

    public abstract String getCategory();

    public abstract String getCategoryLabel();

    // Handles standard features using the existing project logic.
    protected List<String> standardFeatures() {
        List<String> result = new ArrayList<>();
        if (fourByFour) {
            result.add("4x4 drive");
        }
        return result;
    }

    // Returns an existing value or related record.
    public List<String> getAllFeatures() {
        List<String> all = this.standardFeatures();
        for (String feature : this.features) {
            if (!all.contains(feature)) {
                all.add(feature);
            }
        }
        return all;
    }

    // Calculates the value using the existing formula.
    public double calculateCost(int durationHours) {
        if (durationHours > 6) {
            return price * 2;
        }
        return price;
    }

    // Handles ensure bookable for using the existing project logic.
    public void ensureBookableFor(int guests) {
        if (!this.available) {
            throw ApiException.badRequest(this.name + " is not available");
        }
        if (guests > this.capacity) {
            throw ApiException.badRequest(this.name + " seats a maximum of " + this.capacity + " guests");
        }
    }
}
