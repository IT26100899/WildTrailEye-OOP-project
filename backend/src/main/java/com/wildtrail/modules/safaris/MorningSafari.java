package com.wildtrail.modules.safaris;

// MorningSafari: Java class for the WildTrail application.
public final class MorningSafari
extends SafariPackage {
    public MorningSafari(int id, String name, int destinationId, String destinationName, int durationHours, String startTime, int maxGuests, double basePrice, String description, String imageUrl, boolean active) {
        super(id, name, destinationId, destinationName, durationHours, startTime, maxGuests, basePrice, description, imageUrl, active);
    }

    @Override
    // Returns an existing value or related record.
    public String getType() {
        return "MORNING";
    }

    @Override
    // Returns an existing value or related record.
    public String getTypeLabel() {
        return "Morning Safari";
    }

    @Override
    // Returns an existing value or related record.
    public String getPricingNote() {
        return "per guest";
    }

    @Override
    // Returns an existing value or related record.
    public String[] getInclusions() {
        return new String[]{"Park entrance assistance", "Experienced tracker", "Bottled water", "Packed breakfast", "Sunrise game drive"};
    }
}
