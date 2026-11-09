package com.wildtrail.modules.safaris;

import com.wildtrail.exception.ApiException;

// SafariPackage: Java class for the WildTrail application.
public abstract class SafariPackage {
    private final int id;
    private final String name;
    private final int destinationId;
    private final String destinationName;
    private final int durationHours;
    private final String startTime;
    private final int maxGuests;
    private final double basePrice;
    private final String description;
    private final String imageUrl;
    private final boolean active;

    protected SafariPackage(int id, String name, int destinationId, String destinationName, int durationHours, String startTime, int maxGuests, double basePrice, String description, String imageUrl, boolean active) {
        this.id = id;
        this.name = name;
        this.destinationId = destinationId;
        this.destinationName = destinationName;
        this.durationHours = durationHours;
        this.startTime = startTime;
        this.maxGuests = maxGuests;
        this.basePrice = basePrice;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
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
    public int getDestinationId() {
        return this.destinationId;
    }

    // Returns an existing value or related record.
    public String getDestinationName() {
        return this.destinationName;
    }

    // Returns an existing value or related record.
    public int getDurationHours() {
        return this.durationHours;
    }

    // Returns an existing value or related record.
    public String getStartTime() {
        return this.startTime;
    }

    // Returns an existing value or related record.
    public int getMaxGuests() {
        return this.maxGuests;
    }

    // Returns an existing value or related record.
    public double getBasePrice() {
        return this.basePrice;
    }

    // Returns an existing value or related record.
    public String getDescription() {
        return this.description;
    }

    // Returns an existing value or related record.
    public String getImageUrl() {
        return this.imageUrl;
    }

    // Checks a condition and returns true or false.
    public boolean isActive() {
        return this.active;
    }

    public abstract String getType();

    public abstract String getTypeLabel();

    public abstract String getPricingNote();

    public abstract String[] getInclusions();

    // Calculates the value using the existing formula.
    public double calculatePrice(int guests) {
        this.validateGuests(guests);
        return SafariPackage.round(this.basePrice * (double)guests);
    }

    // Checks input before continuing.
    public void validateGuests(int guests) {
        if (guests < 1) {
            throw ApiException.badRequest("At least one guest is required");
        }
        if (guests > this.maxGuests) {
            throw ApiException.badRequest(this.name + " allows a maximum of " + this.maxGuests + " guests");
        }
    }

    // Checks a condition and returns true or false.
    public boolean isBookable() {
        return this.active;
    }

    // Handles round using the existing project logic.
    public static double round(double amount) {
        return java.math.BigDecimal.valueOf(amount).setScale(2,java.math.RoundingMode.HALF_UP).doubleValue();
    }
}
