package com.wildtrail.modules.safaris;

// PhotographySafari: Java class for the WildTrail application.
public final class PhotographySafari
extends SafariPackage {
    public PhotographySafari(int id, String name, int destinationId, String destinationName, int durationHours, String startTime, int maxGuests, double basePrice, String description, String imageUrl, boolean active) {
        super(id, name, destinationId, destinationName, durationHours, startTime, maxGuests, basePrice, description, imageUrl, active);
    }

    @Override
    // Returns an existing value or related record.
    public String getType() {
        return "PHOTOGRAPHY";
    }

    @Override
    // Returns an existing value or related record.
    public String getTypeLabel() {
        return "Photography Safari";
    }

    @Override
    // Returns an existing value or related record.
    public String getPricingNote() {
        return "per guest + $25 gear fee";
    }

    @Override
    // Returns an existing value or related record.
    public String[] getInclusions() {
        return new String[]{"Park entrance assistance", "Experienced tracker", "Bottled water", "Photographer guide", "Bean bags & mounts", "Golden-hour access"};
    }

    @Override
    // Calculates the value using the existing formula.
    public double calculatePrice(int guests) {
        return PhotographySafari.round(super.calculatePrice(guests) + 25.0);
    }
}
