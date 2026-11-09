package com.wildtrail.modules.safaris;

// PrivateSafari: Java class for the WildTrail application.
public final class PrivateSafari
extends SafariPackage {
    public PrivateSafari(int id, String name, int destinationId, String destinationName, int durationHours, String startTime, int maxGuests, double basePrice, String description, String imageUrl, boolean active) {
        super(id, name, destinationId, destinationName, durationHours, startTime, maxGuests, basePrice, description, imageUrl, active);
    }

    @Override
    // Returns an existing value or related record.
    public String getType() {
        return "PRIVATE";
    }

    @Override
    // Returns an existing value or related record.
    public String getTypeLabel() {
        return "Private Safari";
    }

    @Override
    // Returns an existing value or related record.
    public String getPricingNote() {
        return "flat price per party";
    }

    @Override
    // Returns an existing value or related record.
    public String[] getInclusions() {
        return new String[]{"Park entrance assistance", "Experienced tracker", "Bottled water", "Exclusive jeep", "Senior naturalist", "Flexible timing"};
    }

    @Override
    // Calculates the value using the existing formula.
    public double calculatePrice(int guests) {
        this.validateGuests(guests);
        return this.getBasePrice();
    }
}
