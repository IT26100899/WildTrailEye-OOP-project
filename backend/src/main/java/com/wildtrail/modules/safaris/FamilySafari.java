package com.wildtrail.modules.safaris;

// FamilySafari: Java class for the WildTrail application.
public final class FamilySafari
extends SafariPackage {
    public FamilySafari(int id, String name, int destinationId, String destinationName, int durationHours, String startTime, int maxGuests, double basePrice, String description, String imageUrl, boolean active) {
        super(id, name, destinationId, destinationName, durationHours, startTime, maxGuests, basePrice, description, imageUrl, active);
    }

    @Override
    // Returns an existing value or related record.
    public String getType() {
        return "FAMILY";
    }

    @Override
    // Returns an existing value or related record.
    public String getTypeLabel() {
        return "Family Safari";
    }

    @Override
    // Returns an existing value or related record.
    public String getPricingNote() {
        return "covers 4 guests \u00b7 +$15 each extra";
    }

    @Override
    // Returns an existing value or related record.
    public String[] getInclusions() {
        return new String[]{"Park entrance assistance", "Experienced tracker", "Bottled water", "Kids activity pack", "Snack box", "Child seats"};
    }

    @Override
    // Calculates the value using the existing formula.
    public double calculatePrice(int guests) {
        this.validateGuests(guests);
        return FamilySafari.round(this.getBasePrice() + (double)Math.max(0, guests - 4) * 15.0);
    }
}
